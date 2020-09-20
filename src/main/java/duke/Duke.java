package duke;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {
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
        storage.loadListFromFile(tasks.getTaskList());
        ui.readUserCommandLoop(tasks.getTaskList());
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
