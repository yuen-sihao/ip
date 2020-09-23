package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Concludes the interaction with user.
 */
public class ByeCommand extends Command {
    /**
     * Executes the Bye command.
     *
     * @param tasks List of tasks.
     * @param ui UI of the program.
     * @param storage File that the list is saved to.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbyeMessage();
    }

    /**
     * Returns decision to terminate the program.
     *
     * @return decision.
     */
    public Boolean isExit() {
        return true;
    }
}
