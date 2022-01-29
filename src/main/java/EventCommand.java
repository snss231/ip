import java.time.LocalDate;

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
        ui.addTaskMessage(event, tasks.count());
    }
}
