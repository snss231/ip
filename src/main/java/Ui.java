import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Something went wrong while loading data/duke.txt.");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void addTaskMessage(Task task, int count) {
       System.out.println("Got it. I've added this task:\n\t"
                + task
                + "\nNow you have " + count + " tasks in the list.");
    }

    public void print(String s) {
        System.out.println(s);
    }
}
