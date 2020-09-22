package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private static final String MESSAGE_FIND_VALID = "Here are some matching tasks I found you:";
    private static final String MESSAGE_FIND_INVALID = "I can't find any matching tasks.";

    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

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

    public Boolean isExit() {
        return false;
    }
}
