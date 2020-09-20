package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {

    private String description;

    public DoneCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList.checkValidityOfCompletedTask(tasks, description);
    }

    public Boolean isExit() {
        return false;
    }
}
