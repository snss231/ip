package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private LocalDate deadline;
    private String description;
    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline dl = new Deadline(description, deadline);
        tasks.add(dl);
        storage.save(tasks);
        ui.showAddTask(dl, tasks.count());
    }
}
