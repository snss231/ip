package duke;

import duke.task.Task;

/**
 * Responsible for reading user input and to print messages on the command line.
 */
public class Ui {

    /**
     * Prints a welcome message to the user.
     */
    public static String greet() {
        return "Hello bro.\nWhat can I do for you bro?";
    }

    /**
     * Informs the user that there was an error with loading the list of tasks at {PROJECT_ROOT}/data/duke.txt
     */
    public static String showLoadingError() {
        return "Something went wrong while loading data/duke.txt bro.";
    }

    /**
     * Prints the error message to the command line.
     *
     * @param message The error message to be printed.
     */
    public static String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Notifies the user upon task successfully added and prints the number of tasks in the list.
     *
     * @param task The task added.
     * @param count The total number of tasks in the list.
     */
    public static String showAddTask(Task task, int count) {
        return "Got it bro. I've added this task:\n\t"
               + task
               + "\nNow you have " + count + " tasks in the list.";
    }

    /**
     * Notifies the user upon task successfully deleted and prints the number of tasks in the list.
     *
     * @param task The task deleted.
     */
    public static String showDeleteTask(Task task) {
        return "Deleted this task:\n" + task;
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static String sayBye() {
        return "Bye bro, see you again soon.";
    }

    /**
     * Prints all the tasks in the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public static String showTasks(TaskList tasks) {
        return "Here are your tasks bro:\n" + tasks;
    }

    /**
     * Informs the user that there are no tasks.
     */
    public static String showNoTasks() {
        return "There are no tasks yet, bro.";
    }

    /**
     * Notifies the user upon task successfully marked.
     *
     * @param task The marked task.
     */
    public static String showMarkTask(Task task) {
        return "Ok bro, marked this task:\n" + task;
    }

    /**
     * Notifies the user upon task successfully unmarked.
     *
     * @param task The unmarked task.
     */
    public static String showUnmarkTask(Task task) {
        return "Ok bro, unmarked this task:\n" + task;
    }

    /**
     * Returns the user the tasks matching the search keyword.
     *
     * @param results The filtered tasks.
     */
    public static String showFindTasks(TaskList results) {
        return "Here are the matching tasks in your list:\n" + results;
    }

    /**
     * Returns a string informing the user that there are no tasks found.
     *
     * @param searchTerm The search term.
     */
    public static String showFindNoTasks(String searchTerm) {
        return "There are no tasks with the search term \"" + searchTerm + "\" bro.";
    }
}
