package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for reading user input and to print messages on the command line.
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates a new Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads a line from standard input.
     *
     * @return The user's input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a welcome message to the user.
     */
    public void greet() {
        System.out.println("Hello bro.\nWhat can I do for you bro?");
    }

    /**
     * Informs the user that there was an error with loading the list of tasks at {PROJECT_ROOT}/data/duke.txt
     */
    public void showLoadingError() {
        System.out.println("Something went wrong while loading data/duke.txt bro.");
    }

    /**
     * Prints the error message to the command line.
     *
     * @param message The error message to be printed.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Notifies the user upon task successfully added and prints the number of tasks in the list.
     *
     * @param task The task added.
     * @param count The total number of tasks in the list.
     */
    public void showAddTask(Task task, int count) {
       System.out.println("Got it bro. I've added this task:\n\t"
                + task
                + "\nNow you have " + count + " tasks in the list.");
    }

    /**
     * Notifies the user upon task successfully deleted and prints the number of tasks in the list.
     *
     * @param task The task deleted.
     */
    public void showDeleteTask(Task task) {
        System.out.println("Deleted this task:\n" + task);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void sayBye() {
        System.out.println("Bye bro, see you again soon.");
    }

    /**
     * Prints all the tasks in the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public void showTasks(TaskList tasks) {
        System.out.println(
                tasks.isEmpty()
                ? "There are no tasks yet, bro."
                : "Here are your tasks bro:\n" + tasks);
    }

    /**
     * Notifies the usre upon task successfully marked.
     *
     * @param task The marked task.
     */
    public void showMarkTask(Task task) {
        System.out.println("Ok bro, marked this task:\n" + task);
    }

    /**
     * Notifies the user upon task successfully unmarked.
     *
     * @param task The unmarked task.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("Ok bro, unmarked this task:\n" + task);
    }

    public void showFindTasks(TaskList results, String keyword) {
        System.out.println(
                results.isEmpty()
                        ? "There are no tasks with the keyword \"" + keyword + "\" bro."
                        : "Here are the matching tasks in your list:\n" + results);
    }
}
