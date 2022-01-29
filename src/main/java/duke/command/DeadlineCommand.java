package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.time.LocalDate;

/**
 * Represents a command that creates a new Deadline task.
 */
public class DeadlineCommand extends Command {

    private LocalDate deadline;
    private String description;
    public static final String COMMAND_WORD = "deadline";

    /**
     * Creates a new DeadlineCommand.
     *
     * @param description Description of deadline.
     * @param deadline Date representing the deadline.
     */
    public DeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Creates a new deadline task, adds it to the list of tasks, stores the tasks into the user's storage and
     * notifies the user upon successful completion.
     *
     * @param tasks List of user's tasks.
     * @param ui Handles interactions with the user.
     * @param storage Handles storage of tasks in the user's memory.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline dl = new Deadline(description, deadline);
        tasks.add(dl);
        storage.save(tasks);
        ui.showAddTask(dl, tasks.count());
    }
}
