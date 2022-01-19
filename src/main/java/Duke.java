import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<String> items = new ArrayList<>();

    public static void main(String[] args) {

        greet();

        Scanner sc = new Scanner(System.in);

        loop: while (true) {
            String command = sc.nextLine();
            switch(command) {
                case "bye":
                    sayBye();
                    break loop;
                case "list":
                    listItems();
                    break;
                default:
                    addItem(command);
                    break;
            }
        }
    }

    static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void addItem(String command) {
        items.add(command);
        System.out.println("added: " + command);
    }

    static void listItems() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }

}
