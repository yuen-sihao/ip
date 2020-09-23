package duke.parser;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

public class Parser {

    /** Types of user commands accepted by this program */
    private static final String USER_COMMAND_TODO = "todo";
    private static final String USER_COMMAND_DEADLINE = "deadline";
    private static final String USER_COMMAND_EVENT = "event";
    private static final String USER_COMMAND_DELETE = "delete";
    private static final String USER_COMMAND_DONE = "done";
    private static final String USER_COMMAND_FIND = "find";
    private static final String USER_COMMAND_LIST = "list";
    private static final String USER_COMMAND_BYE = "bye";

    /**
     * Parses and determine the command to execute.
     *
     * @param command Command entered by the user.
     * @return Command object of the specified command type.
     */
    public static Command parse(String command) {
        Command currentCommand = null;
        if (command.equals(USER_COMMAND_BYE)) {
            currentCommand = new ByeCommand();
        } else if (command.equals(USER_COMMAND_LIST)) {
            currentCommand = new ListCommand();
        } else {
            int indexToSplit = command.indexOf(' ');
            String userCommand = command.substring(0, indexToSplit);
            String description = command.substring(indexToSplit);
            description = description.trim();
            switch (userCommand) {
            case USER_COMMAND_TODO:
            case USER_COMMAND_DEADLINE:
            case USER_COMMAND_EVENT:
                currentCommand = new AddCommand(userCommand, description);
                break;
            case USER_COMMAND_DONE:
                currentCommand = new DoneCommand(description);
                break;
            case USER_COMMAND_DELETE:
                currentCommand = new DeleteCommand(description);
                break;
            case USER_COMMAND_FIND:
                currentCommand = new FindCommand(description);
                break;
            default:
                break;
            }
        }
        return currentCommand;
    }
}
