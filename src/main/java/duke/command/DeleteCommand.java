package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private String description;

    public DeleteCommand (String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList.checkValidityOfTaskToDelete(tasks, description);
    }

    public Boolean isExit() {
        return false;
    }
}
