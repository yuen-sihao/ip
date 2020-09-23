package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Finds a task from the list.
 */
public class FindCommand extends Command {
    /** Error messages by this program */
    private static final String MESSAGE_FIND_VALID = "Here are some matching tasks I found you:";
    private static final String MESSAGE_FIND_INVALID = "I can't find any matching tasks.";

    private String description;

    /**
     * Class constructor of the Find command.
     *
     * @param description Description of the task to be found.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the Find command.
     *
     * @param tasks List that contains the task to be found.
     * @param ui UI of the program.
     * @param storage File that holds the list of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Boolean hasMatch = false;
        int numberOfTaskFound = 0;
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            if (tasks.getTaskList().get(i).getDescription().contains(description)) {
                if (!hasMatch) {
                    System.out.println(MESSAGE_FIND_VALID);
                }
                hasMatch = true;
                System.out.println((numberOfTaskFound + 1) + "." + tasks.getTaskList().get(i).toString());
                numberOfTaskFound++;
            }
        }
        if (!hasMatch) {
            System.out.println(MESSAGE_FIND_INVALID);
        }
        ui.printSingleLine();
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
