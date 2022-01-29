package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that lists all the tasks in the user's task list.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Prints the user's tasks.
     *
     * @param tasks List of user's tasks.
     * @param ui Handles interactions with the user.
     * @param storage Handles storage of tasks in the user's memory.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}