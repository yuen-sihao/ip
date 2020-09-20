package duke.ui;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String MESSAGE_DUKE_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String MESSAGE_WELCOME = "Hello from" + System.lineSeparator()
            + MESSAGE_DUKE_LOGO;
    private static final String MESSAGE_GREETING = "Hello! I'm Duke" + System.lineSeparator()
            + "How can I help you?";
    private static final String MESSAGE_GOODBYE = "Bye. See you soon!";

    private static final String LINE_SPACING = "_____________________________________________";

    private static final String MESSAGE_TASK_CREATE = "One more thing you got to do. Press on!";
    private static final String MESSAGE_EMPTY_LIST = "Wow! I see that your list is empty";
    private static final String MESSAGE_LIST_HEADER = "Here are the tasks in your list:";
    private static final String MESSAGE_TASK_DONE = "Nice. One more down!";
    private static final String MESSAGE_TASK_DELETE = "A non-essential task ya? Ok removed!";

    private static final String ERROR_INVALID_TASK_TYPE = "I'm sorry I don't understand you."
            + System.lineSeparator() + "Would you like to tell me again?";
    private static final String ERROR_INVALID_IO = "I/O error has occurred";

    private static final String USER_COMMAND_LIST = "list";
    private static final String USER_COMMAND_BYE = "bye";

    private static final String DATA_FILE_DIR = "data/";
    private static final String DATA_FILE = DATA_FILE_DIR + "/duke.txt";

    private final Scanner input;

    public Ui() {
        input = new Scanner(System.in);
    }

    public void readUserCommandLoop(ArrayList<Task> tasks) {
        String command;
        boolean isFinished = false;
        while (!isFinished && input.hasNextLine()) {
            command = input.nextLine();
            printSingleLine();
            if (command.equals(USER_COMMAND_BYE)) {
                printGoodbyeMessage();
                isFinished = true;
            } else if (command.equals(USER_COMMAND_LIST)) {
                printListOfTask(tasks);
            } else {
                Parser.updateListOfTask(command, tasks);
                try {
                    Storage.saveListToFile(tasks, DATA_FILE);
                } catch (IOException e) {
                    System.out.println(ERROR_INVALID_IO);
                }
            }
        }
    }

    public static void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
        printSingleLine();
        System.out.println(MESSAGE_GREETING);
        printSingleLine();
    }

    public static void printGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
        printSingleLine();
    }

    public static void printSingleLine() {
        System.out.println(LINE_SPACING);
    }

    public static void printDetailsOfAddedTask(ArrayList<Task> tasks, Task newTask) {
        System.out.println(MESSAGE_TASK_CREATE);
        System.out.println(newTask.toString());
        countNumberOfTask(tasks);
    }

    public static void printDetailsOfDeletedTask(ArrayList<Task> tasks, Task tasksToDelete) {
        System.out.println(MESSAGE_TASK_DELETE);
        System.out.println(tasksToDelete.toString());
        countNumberOfTask(tasks);
    }

    public static void printListOfTask(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println(MESSAGE_EMPTY_LIST);
        } else {
            System.out.println(MESSAGE_LIST_HEADER);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
        printSingleLine();
    }

    public static void printDetailsOfCompletedTask(Task task) {
        System.out.println(MESSAGE_TASK_DONE);
        System.out.println(task.toString());
        printSingleLine();
    }

    public static void printInvalidTaskMessage() {
        System.out.println(ERROR_INVALID_TASK_TYPE);
        printSingleLine();
    }

    private static void countNumberOfTask(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printSingleLine();
    }
}
