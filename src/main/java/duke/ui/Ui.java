package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

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

    public Scanner input;

    public Ui() {
        input = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
        printSingleLine();
        System.out.println(MESSAGE_GREETING);
        printSingleLine();
    }

    public void printGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
        printSingleLine();
    }

    public static void printSingleLine() {
        System.out.println(LINE_SPACING);
    }

    public static void printDetailsOfAddedTask(TaskList tasks, Task newTask) {
        System.out.println(MESSAGE_TASK_CREATE);
        System.out.println(newTask.toString());
        countNumberOfTask(tasks);
    }

    public static void printDetailsOfDeletedTask(TaskList tasks, Task tasksToDelete) {
        System.out.println(MESSAGE_TASK_DELETE);
        System.out.println(tasksToDelete.toString());
        countNumberOfTask(tasks);
    }

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

    public static void printDetailsOfCompletedTask(Task task) {
        System.out.println(MESSAGE_TASK_DONE);
        System.out.println(task.toString());
        printSingleLine();
    }

    public void printInvalidTaskMessage() {
        System.out.println(ERROR_INVALID_TASK_TYPE);
        printSingleLine();
    }

    private static void countNumberOfTask(TaskList tasks) {
        System.out.println("Now you have " + tasks.getTaskList().size() + " tasks in the list.");
        printSingleLine();
    }
}
