package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Marks a task as done.
 */
public class DoneCommand extends Command {
    private String description;

    /**
     * Class constructor of the Done command.
     *
     * @param description Description of task to be marked as done.
     */
    public DoneCommand(String description) {
        this.description = description;
    }

    /**
     * Checks the validity of the completed task.
     * If the completed task is valid, task is marked as done.
     *
     * @param tasks List of tasks that the completed task is in.
     * @param ui UI of the program.
     * @param storage File to save the list to after marking task as done.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList.checkValidityOfCompletedTask(tasks, description);
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
