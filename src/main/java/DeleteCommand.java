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
            ui.print("Deleted this task:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task " + (index + 1) + " does not exist.");
        }
    }
}
