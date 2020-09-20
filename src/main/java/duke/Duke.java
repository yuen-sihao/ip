package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    private static final String DATA_FILE_DIR = "data/";
    private static final String DATA_FILE = DATA_FILE_DIR + "/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String pathOfDataFile) {
        ui = new Ui();
        storage = new Storage(pathOfDataFile);
        tasks = new TaskList();
    }

    public void run() {
        ui.printWelcomeMessage();
        storage.loadListFromFile(tasks);
        readUserCommandLoop(tasks);
    }

    public void readUserCommandLoop(TaskList tasks) {
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = Parser.parse(ui.input.nextLine());
                command.execute(tasks, ui, storage);
                storage.saveListToFile(tasks, DATA_FILE);
                isExit = command.isExit();
            } catch (NullPointerException | StringIndexOutOfBoundsException | IOException e) {
                ui.printInvalidTaskMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
