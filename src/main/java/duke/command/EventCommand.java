package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {

    private String description;
    private String at;

    public static final String COMMAND_WORD = "event";

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, at);
        tasks.add(event);
        storage.save(tasks);
        ui.showAddTask(event, tasks.count());
    }
}
