public class TodoCommand extends Command {

    private String description;
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks);
        ui.addTaskMessage(todo, tasks.count());
    }
}