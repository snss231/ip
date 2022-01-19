import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        greet();

        Scanner sc = new Scanner(System.in);

        loop: while (true) {
            String rawInput = sc.nextLine();
            String[] input = rawInput.split(" ");
            String command = input[0];
            switch(command) {
                case "bye":
                    sayBye();
                    break loop;
                case "list":
                    listItems();
                    break;
                case "mark":
                    mark(Integer.parseInt(input[1]) - 1);
                    break;
                case "unmark":
                    unmark(Integer.parseInt(input[1]) - 1);
                    break;
                default:
                    addTask(rawInput);
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

    static void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("added: " + description);
    }

    static void listItems() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }
}
