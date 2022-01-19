import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        greet();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                sayBye();
                break;
            } else {
                echo(command);
            }
        }
    }

    static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void echo(String command) {
        System.out.println(command);
    }

}
