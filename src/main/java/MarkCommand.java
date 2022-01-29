public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.mark(index);
            storage.save(tasks);
            ui.print("Ok bro, marked this task:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task " + (index + 1) + " does not exist.");
        }
    }
}
