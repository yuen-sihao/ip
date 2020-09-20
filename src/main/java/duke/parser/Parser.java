package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;

public class Parser {

    private static final String USER_COMMAND_TODO = "todo";
    private static final String USER_COMMAND_DEADLINE = "deadline";
    private static final String USER_COMMAND_EVENT = "event";
    private static final String USER_COMMAND_DELETE = "delete";
    private static final String USER_COMMAND_DONE = "done";
    private static final String USER_COMMAND_LIST = "list";
    private static final String USER_COMMAND_BYE = "bye";

    public static Command parse(String command) {
        Command currentCommand = null;
        if (command.equals(USER_COMMAND_BYE)) {
            currentCommand = new ByeCommand();
        } else if (command.equals(USER_COMMAND_LIST)) {
            currentCommand = new ListCommand();
        } else {
            int indexToSplit = command.indexOf(' ');
            String typeOfTask = command.substring(0, indexToSplit);
            String description = command.substring(indexToSplit);
            description = description.trim();

            switch (typeOfTask) {
            case USER_COMMAND_TODO:
            case USER_COMMAND_DEADLINE:
            case USER_COMMAND_EVENT:
                currentCommand = new AddCommand(typeOfTask, description);
                break;
            case USER_COMMAND_DONE:
                currentCommand = new DoneCommand(description);
                break;
            case USER_COMMAND_DELETE:
                currentCommand = new DeleteCommand(description);
                break;
            default:
                break;
            }
        }
        return currentCommand;
    }
}
