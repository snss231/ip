package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.split(" ", 2); //return [commandWord, restOfCommand]
        String commandWord = inputs[0];

        switch(commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD: {
            int index = -1;
            try {
                index = Integer.valueOf(inputs[1]) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid parameter(s). Usage: mark [taskNumber]");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). duke.task.Task " + (index + 1) + " does not exist");
            }
        }
        case UnmarkCommand.COMMAND_WORD: {
            int index = -1;
            try {
                index = Integer.valueOf(inputs[1]) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid parameter(s). Usage: mark [taskNumber]");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). duke.task.Task " + (index + 1) + " does not exist");
            }
        }
        case TodoCommand.COMMAND_WORD:
            try {
                String description = inputs[1];
                return new TodoCommand(description);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). Usage: todo [description]");
            }
        case DeadlineCommand.COMMAND_WORD:
            try {
                String[] args = inputs[1].split(" /by ", 2);
                String description = args[0];
                LocalDate deadline = LocalDate.parse(args[1]);
                return new DeadlineCommand(description, deadline);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please specify your date in the format \"yyyy-mm-dd\"");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). Usage: deadline [description] /by [time]");
            }
        case EventCommand.COMMAND_WORD:
            try {
                String[] args = inputs[1].split(" /at ", 2);
                String description = args[0];
                String at = args[1];
                return new EventCommand(description, at);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). Usage: event [description] /at [time]");
            }
        case DeleteCommand.COMMAND_WORD: {
            int index = -1;
            try {
                index = Integer.parseInt(inputs[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid parameter(s). Usage: delete [taskNumber]");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameter(s). duke.task.Task " + (index + 1) + " does not exist");
            }
        }
        case FindCommand.COMMAND_WORD:
            try {
                String keyword = inputs[1];
                return new FindCommand(keyword);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Invalid parameters. Usage: find [keyword]");
            }
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            throw new DukeException("Unknown command");
        }
    }
}
