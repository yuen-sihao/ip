package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private static final String USER_COMMAND_TODO = "todo";
    private static final String USER_COMMAND_DEADLINE = "deadline";
    private static final String USER_COMMAND_EVENT = "event";

    private String typeOfTask;
    private String description;

    public AddCommand(String typeOfTask, String description) {
        this.typeOfTask = typeOfTask;
        this.description = description;
    }

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

    public Boolean isExit() {
        return false;
    }
}
