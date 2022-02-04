package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.command.Command;

/**
 * Main driver class for task organisation bot Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Duke.
     */
    public Duke() {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(Ui.showLoadingError());
            tasks = new TaskList();
        }
    }

    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
