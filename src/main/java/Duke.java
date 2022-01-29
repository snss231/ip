import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;

enum Command {
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    BYE
}

public class Duke {
    static final String PROJECT_ROOT = System.getProperty("user.dir");
    static ArrayList<Task> tasks;

    public static void main(String[] args) {
        try {
            String path  = Path.of(PROJECT_ROOT, "data", "duke.txt").toString();
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (ArrayList<Task>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
            tasks = new ArrayList<>();
        }

        greet();

        Scanner sc = new Scanner(System.in);

        loop: while (true) {
            String rawInput = sc.nextLine();
            String[] input = rawInput.split(" ", 2);
            Command command;
            try {
                command = Command.valueOf(input[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, I don't know the command " + input[0] + "!");
                continue;
            }

            switch(command) {
            case LIST:
                list();
                break;
            case MARK: {
                int index = -1;
                try {
                    index = Integer.parseInt(input[1]) - 1;
                    mark(index);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid parameter(s). Usage: mark [taskNumber]");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid parameter(s). Task " + (index + 1) + " does not exist");
                }
                break;
            }
            case UNMARK: {
                int index = -1;
                try {
                    index = Integer.parseInt(input[1]) - 1;
                    unmark(index);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid parameter(s). Usage: mark [taskNumber]");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid parameter(s). Task " + (index + 1) + " does not exist");
                }
                break;
            }
            case TODO:
                if (input.length == 1) {
                    System.out.println("Invalid parameter(s). Usage: todo [description]");
                    break;
                }
                String todo = input[1];
                addTodo(todo);
                break;
            case DEADLINE: {
                if (input.length == 1) {
                    System.out.println("Invalid parameter(s). Usage: deadline [description] /by [due date/time]");
                    break;
                }
                String[] parsedDeadline = input[1].split(" /by ");
                if (parsedDeadline.length == 1) {
                    System.out.println("Invalid parameter(s). Usage: deadline [description] /by [due date/time]");
                    break;
                }
                String description = parsedDeadline[0];
                String deadline = parsedDeadline[1];
                addDeadline(description, deadline);
                break;
            }
            case EVENT: {
                if (input.length == 1) {
                    System.out.println("Invalid parameter(s). Usage: event [description] /at [time]");
                    break;
                }
                String[] parsedEvent = input[1].split(" /at ");
                if (parsedEvent.length == 1) {
                    System.out.println("Invalid parameter(s). Usage: event [description] /by [time]");
                    break;
                }
                String description = parsedEvent[0];
                String time = parsedEvent[1];
                addEvent(description, time);
                break;
            }
            case DELETE: {
                int index = -1;
                try {
                    index = Integer.parseInt(input[1]) - 1;
                    tasks.remove(index);
                    saveTasks();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid parameter(s). Usage: mark [taskNumber]");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid parameter(s). Task " + (index + 1) + " does not exist");
                }
                break;
            }
            case BYE:
                sayBye();
                break loop;
            default:
                System.out.println("Unknown command");
                break;
            }
        }
    }

    static void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    static void saveTasks() {
        try {
            String path  = Path.of(PROJECT_ROOT, "data", "duke.txt").toString();
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static void mark(int index) {
        Task task = tasks.get(index);
        task.mark();
        saveTasks();
        System.out.println("Nice! I've marked this task as done:\n\t" + task);
    }

    static void unmark(int index) {
        Task task = tasks.get(index);
        task.unmark();
        saveTasks();
        System.out.println("Ok, I've marked this task as not done yet:\n\t" + task);
    }

    static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void addTodo(String description) {
        Todo todo = new Todo(description);
        addTask(todo);
        System.out.println("Got it. I've added this task:\n\t"
                + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    static void addDeadline(String description, String dueDateTime) {
        Deadline deadline = new Deadline(description, dueDateTime);
        addTask(deadline);
        System.out.println("Got it. I've added this task:\n\t"
                + deadline
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    static void addEvent(String description, String time) {
        Event event = new Event(description, time);
        addTask(event);
        System.out.println("Got it. I've added this task:\n\t"
                + event
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
}
