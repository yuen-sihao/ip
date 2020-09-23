package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Point of entry to Duke.
 */
public class Duke {
    /** Directory and file that the list is saved to */
    private static final String DATA_FILE_DIR = "data/";
    private static final String DATA_FILE = DATA_FILE_DIR + "/duke.txt";

    /** Resources required on startup */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the resources required on startup.
     *
     * @param pathOfDataFile File path to specify the directory and file.
     */
    public Duke(String pathOfDataFile) {
        ui = new Ui();
        storage = new Storage(pathOfDataFile);
        tasks = new TaskList();
    }

    /**
     * Greets the user on startup.
     * Loads the saved tasks from the data file.
     * Interacts with the user until point of termination.
     */
    public void run() {
        ui.printWelcomeMessage();
        storage.loadListFromFile(tasks);
        readUserCommandLoop(tasks);
    }

    /**
     * Executes the program until being notified to terminate the program.
     *
     * @param tasks List that contains the tasks.
     */
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
