package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbyeMessage();
    }

    public Boolean isExit() {
        return true;
    }
}
