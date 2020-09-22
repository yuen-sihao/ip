package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task from the list.
 */
public class DeleteCommand extends Command {
    private String description;

    /**
     * Class constructor of the Done command.
     *
     * @param description Description of task to be deleted.
     */
    public DeleteCommand (String description) {
        this.description = description;
    }

    /**
     * Checks the validity of the task to delete.
     * If the task to delete is valid, the deletion is executed.
     *
     * @param tasks List of tasks to delete task from.
     * @param ui UI of the program.
     * @param storage File to save list to after deleting task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList.checkValidityOfTaskToDelete(tasks, description);
    }

    /**
     * Returns decision to terminate the program.
     *
     * @return decision.
     */
    public Boolean isExit() {
        return false;
    }
}
