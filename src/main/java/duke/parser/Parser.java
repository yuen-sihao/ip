package duke.parser;

import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class Parser {

    private static final String USER_COMMAND_TODO = "todo";
    private static final String USER_COMMAND_DEADLINE = "deadline";
    private static final String USER_COMMAND_EVENT = "event";
    private static final String USER_COMMAND_DELETE = "delete";
    private static final String USER_COMMAND_DONE = "done";

    public static void updateListOfTask(String command, ArrayList<Task> tasks) {
        int indexToSplit = command.indexOf(' ');
        if (indexToSplit == -1) {
            Ui.printInvalidTaskMessage();
        } else {
            String typeOfTask = command.substring(0, indexToSplit);
            String description = command.substring(indexToSplit);
            description = description.trim();
            determineTypeOfTask(tasks, typeOfTask, description);
        }
    }

    public static void determineTypeOfTask(ArrayList<Task> tasks, String typeOfTask, String description) {
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
        case USER_COMMAND_DONE:
            TaskList.checkValidityOfCompletedTask(tasks, description);
            break;
        case USER_COMMAND_DELETE:
            TaskList.checkValidityOfTaskToDelete(tasks, description);
            break;
        default:
            Ui.printInvalidTaskMessage();
            break;
        }
    }
}
