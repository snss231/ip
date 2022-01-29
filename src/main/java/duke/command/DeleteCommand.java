package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTask(index);
            storage.save(tasks);
            ui.showDeleteTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task " + (index + 1) + " does not exist bro.");
        }
    }
}
