package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Represents a command that creates a new Event task.
 */
public class EventCommand extends Command {

    private String description;
    private String at;

    public static final String COMMAND_WORD = "event";

    /**
     * Creates a new EventCommand.
     *
     * @param description Description of deadline.
     * @param at String representing the time that the event occurs.
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Creates a new event task, adds it to the list of tasks, stores the tasks into the user's storage and notifies the
     * user upon successful completion.
     *
     * @param tasks List of user's tasks.
     * @param ui Handles interactions with the user.
     * @param storage Handles storage of tasks in the user's memory.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, at);
        tasks.addTask(event);
        storage.save(tasks);
        ui.showAddTask(event, tasks.count());
    }
}
