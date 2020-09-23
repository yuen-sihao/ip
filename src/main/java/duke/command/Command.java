package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable user command.
 */
public abstract class Command {
    /**
     * Executes the specified user command.
     *
     * @param tasks List of tasks to execute the command on.
     * @param ui UI of the program.
     * @param storage File that holds the list of tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns decision to terminate the program.
     *
     * @return decision.
     */
    public abstract Boolean isExit();
}
