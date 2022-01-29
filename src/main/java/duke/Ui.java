package duke;

import duke.task.Task;
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
        System.out.println("Hello bro.\nWhat can I do for you bro?");
    }

    public void showLoadingError() {
        System.out.println("Something went wrong while loading data/duke.txt bro.");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showAddTask(Task task, int count) {
       System.out.println("Got it bro. I've added this task:\n\t"
                + task
                + "\nNow you have " + count + " tasks in the list.");
    }

    public void showDeleteTask(Task task) {
        System.out.println("Deleted this task:\n" + task);
    }

    public void sayBye() {
        System.out.println("Bye bro, see you again soon.");
    }

    public void showTasks(TaskList tasks) {
        System.out.println(tasks.isEmpty()
                ? "There are no tasks yet, bro."
                : "Here are your tasks bro:\n" + tasks);
    }

    public void showUnmarkTask(Task task) {
        System.out.println("Ok bro, unmarked this task:\n" + task);
    }

    public void showMarkTask(Task task) {
        System.out.println("Ok bro, marked this task:\n" + task);
    }
}
