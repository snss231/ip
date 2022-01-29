package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class representing a response to user input.
 */
public abstract class Command {

    /**
     * The action to be taken in response to the user input.
     *
     * @param tasks List of user's tasks.
     * @param ui Handles interactions with the user.
     * @param storage Handles storage of tasks in the user's memory.
     *
     * @throws DukeException On invalid command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Commands do not cause program to exit by default.
     */
    public boolean isExit() {
        return false;
    }
}