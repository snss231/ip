package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
