package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

/**
 * Represents a command that creates a new Todo task.
 */
public class TodoCommand extends Command {

    private String description;
    public static final String COMMAND_WORD = "todo";

    /**
     * Creates a new TodoCommand.
     *
     * @param description Description of todo.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new todo, adds it into the user's task list, updates the storage and notifies user upon successful
     * completion.
     *
     * @param tasks List of user's tasks.
     * @param ui Handles interactions with the user.
     * @param storage Handles storage of tasks in the user's memory.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks);
        ui.showAddTask(todo, tasks.count());
    }
}