import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

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

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
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
                LocalDate deadline;
                try {
                    deadline = LocalDate.parse(parsedDeadline[1]);
                } catch (DateTimeParseException e) {
                    System.out.println("Sorry, I don't understand the date: \"" + parsedDeadline[1] + "\". " +
                            "Enter your date in the format yyyy-mm-dd.");
                    continue;
                }
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
                    Task task = tasks.remove(index);
                    System.out.println("Deleted task:\n" +
                            task +
                            "\nNow you have " + tasks.count() + " tasks in the list.");
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

    private void mark(int index) {
        Task task = tasks.mark(index);
        storage.save(tasks);
        System.out.println("Nice! I've marked this task as done:\n\t" + task);
    }

    private void unmark(int index) {
        Task task = tasks.unmark(index);
        System.out.println("Ok, I've marked this task as not done yet:\n\t" + task);
    }

    private void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks);
        System.out.println("Got it. I've added this task:\n\t"
                + todo
                + "\nNow you have " + tasks.count() + " tasks in the list.");
    }

    private void addDeadline(String description, LocalDate dueDateTime) {
        Deadline deadline = new Deadline(description, dueDateTime);
        tasks.add(deadline);
        storage.save(tasks);
        System.out.println("Got it. I've added this task:\n\t"
                + deadline
                + "\nNow you have " + tasks.count() + " tasks in the list.");
    }

    private void addEvent(String description, String time) {
        Event event = new Event(description, time);
        tasks.add(event);
        storage.save(tasks);
        System.out.println("Got it. I've added this task:\n\t"
                + event
                + "\nNow you have " + tasks.count() + " tasks in the list.");
    }

    private void list() {
        System.out.println("Here are the tasks in your list:\n" + tasks);

    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
