package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Adds a task to the list.
 */
public class AddCommand extends Command {
    /** Types of task accepted by this program */
    private static final String USER_COMMAND_TODO = "todo";
    private static final String USER_COMMAND_DEADLINE = "deadline";
    private static final String USER_COMMAND_EVENT = "event";

    private String typeOfTask;
    private String description;

    /**
     * Class constructor of the Add command.
     *
     * @param typeOfTask Type of the task to be added.
     * @param description Description of the task to be added.
     */
    public AddCommand(String typeOfTask, String description) {
        this.typeOfTask = typeOfTask;
        this.description = description;
    }

    /**
     * Executes the Add command.
     *
     * @param tasks List of tasks to add task to.
     * @param ui UI of the program.
     * @param storage File to save list to after adding task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (typeOfTask) {
        case USER_COMMAND_TODO:
            TaskList.createTodoTask(tasks, description);
            break;
        case USER_COMMAND_DEADLINE:
            TaskList.checkValidityOfDeadline(tasks, description);
            break;
        case USER_COMMAND_EVENT:
            TaskList.checkValidityOfEvent(tasks, description);
            break;
        }
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
