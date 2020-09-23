package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.Scanner;

/**
 * Handles the interaction with user.
 */
public class Ui {

    /** Messages by this program */
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

    /** Error by this program */
    private static final String ERROR_INVALID_TASK = "I'm sorry I don't understand you."
            + System.lineSeparator() + "Would you like to tell me again?";

    public Scanner input;

    /**
     * Reads the user input.
     */
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Prints the welcome messages when the program starts.
     */
    public void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
        printSingleLine();
        System.out.println(MESSAGE_GREETING);
        printSingleLine();
    }

    /**
     * Prints the goodbye message when the program terminates.
     */
    public void printGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
        printSingleLine();
    }

    /**
     * Prints a line as a divider.
     */
    public static void printSingleLine() {
        System.out.println(LINE_SPACING);
    }

    /**
     * Prints details of the added task.
     *
     * @param tasks List that the task is added to.
     * @param newTask Task that is added.
     */
    public static void printDetailsOfAddedTask(TaskList tasks, Task newTask) {
        System.out.println(MESSAGE_TASK_CREATE);
        System.out.println(newTask.toString());
        countNumberOfTask(tasks);
    }

    /**
     * Prints details of the deleted task.
     *
     * @param tasks List that the task is deleted from.
     * @param tasksToDelete Task that is deleted.
     */
    public static void printDetailsOfDeletedTask(TaskList tasks, Task tasksToDelete) {
        System.out.println(MESSAGE_TASK_DELETE);
        System.out.println(tasksToDelete.toString());
        countNumberOfTask(tasks);
    }

    /**
     * Prints list of tasks.
     *
     * @param tasks List that contains the tasks.
     */
    public static void printListOfTask(TaskList tasks) {
        if (tasks.getTaskList().size() == 0) {
            System.out.println(MESSAGE_EMPTY_LIST);
        } else {
            System.out.println(MESSAGE_LIST_HEADER);
            for (int i = 0; i < tasks.getTaskList().size(); i++) {
                System.out.println((i + 1) + "." + tasks.getTaskList().get(i).toString());
            }
        }
        printSingleLine();
    }

    /**
     * Prints details of the completed task.
     *
     * @param task List that contains the completed task.
     */
    public static void printDetailsOfCompletedTask(Task task) {
        System.out.println(MESSAGE_TASK_DONE);
        System.out.println(task.toString());
        printSingleLine();
    }

    /**
     * Prints error message for invalid tasks.
     */
    public void printInvalidTaskMessage() {
        System.out.println(ERROR_INVALID_TASK);
        printSingleLine();
    }

    private static void countNumberOfTask(TaskList tasks) {
        System.out.println("Now you have " + tasks.getTaskList().size() + " tasks in the list.");
        printSingleLine();
    }
}
