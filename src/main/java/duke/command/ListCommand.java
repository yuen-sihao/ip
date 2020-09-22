package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Lists the tasks found in the list.
 */
public class ListCommand extends Command {
    /**
     * Executes the List command.
     *
     * @param tasks TaskList that contains the tasks to be listed.
     * @param ui UI of the program.
     * @param storage File that the list is saved to.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printListOfTask(tasks);
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
