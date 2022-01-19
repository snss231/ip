import java.util.Scanner;
import java.util.ArrayList;

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

    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        greet();

        Scanner sc = new Scanner(System.in);

        loop: while (true) {
            String rawInput = sc.nextLine();
            String[] input = rawInput.split(" ", 2);
            Command command = Command.valueOf(input[0].toUpperCase());

            switch(command) {
                case LIST:
                    list();
                    break;
                case MARK: {
                    if (input.length == 1) {
                        System.out.println("Invalid parameter(s). Usage: mark [taskNumber]");
                        break;
                    }
                    int index = Integer.parseInt(input[1]) - 1;
                    if (index >= tasks.size() || index < 0) {
                        System.out.println("Invalid parameter(s). Task " + (index + 1) + " does not exist");
                        break;
                    }
                    mark(index);
                    break;
                }
                case UNMARK: {
                    if (input.length == 1) {
                        System.out.println("Invalid parameter(s). Usage: unmark [taskNumber]");
                        break;
                    }

                    int index = Integer.parseInt(input[1]) - 1;
                    if (index >= tasks.size() || index < 0) {
                        System.out.println("Invalid parameter(s). Task " + (index + 1) + " does not exist");
                        break;
                    }

                    unmark(Integer.parseInt(input[1]) - 1);
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
                    if (input.length == 1) {
                        System.out.println("Invalid parameter(s). Usage: delete [taskNumber]");
                        break;
                    }

                    int index = Integer.parseInt(input[1]) - 1;
                    if (index >= tasks.size() || index < 0) {
                        System.out.println("Invalid parameter(s). Task " + (index + 1) + " does not exist");
                        break;
                    }

                    Task deletedTask = tasks.remove(index);
                    System.out.println("Noted. I've removed this task:\n\t" +
                            deletedTask +
                            "\nNow you have " + tasks.size() + " tasks in the list.");
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

    static void mark(int index) {
        Task task = tasks.get(index);
        task.mark();
        System.out.println("Nice! I've marked this task as done:\n\t" + task);
    }

    static void unmark(int index) {
        Task task = tasks.get(index);
        task.unmark();
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
        tasks.add(todo);
        System.out.println("Got it. I've added this task:\n\t"
                + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    static void addDeadline(String description, String dueDateTime) {
        Deadline deadline = new Deadline(description, dueDateTime);
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:\n\t"
                + deadline
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    static void addEvent(String description, String time) {
        Event event = new Event(description, time);
        tasks.add(event);
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
