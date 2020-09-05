package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASK = 100;

    public static void main(String[] args) {
        printWelcomeMessage();
        String command;
        Task[] tasks = new Task[MAX_NUMBER_OF_TASK];
        boolean isFinished = false;
        Scanner input = new Scanner(System.in);
        while (!isFinished && input.hasNextLine()) {
            command = input.nextLine();
            printSingleLine();
            if (command.equals("bye")) {
                printGoodbyeMessage();
                isFinished = true;
            } else if (command.equals("list")) {
                printListOfTask(tasks);
            } else {
                updateListOfTask(command, tasks);
            }
        }
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printSingleLine();
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("How can I help you?");
        printSingleLine();
    }

    private static void printGoodbyeMessage() {
        System.out.println("Bye. See you soon!");
        printSingleLine();
    }

    private static void printListOfTask(Task[] tasks) {
        if (Task.getNumberOfTask() == 0) {
            System.out.println("Wow! I see that your list is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getNumberOfTask(); i++) {
                System.out.println((i + 1) + "." + tasks[(i + 1)].toString());
            }
        }
        printSingleLine();
    }

    private static void printDetailsOfAddedTask(Task newTask) {
        System.out.println("One more thing you got to do. Press on!");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + Task.getNumberOfTask() + " tasks in the list.");
        printSingleLine();
    }

    private static void updateTaskAsComplete(Task[] tasks, String description) {
        int taskNumber = Integer.parseInt(description);
        tasks[taskNumber].markAsDone();
        printDetailsOfCompletedTask(tasks[taskNumber]);
    }

    private static void printDetailsOfCompletedTask(Task task) {
        System.out.println("Nice. One more down!");
        System.out.println(task.toString());
        printSingleLine();
    }

    private static void updateListOfTask(String command, Task[] tasks) {
        int indexToSplit = command.indexOf(' ');
        if (indexToSplit == -1) {
            printInvalidTaskMessage();
        } else {
            String typeOfTask = command.substring(0, indexToSplit);
            String description = command.substring(indexToSplit);
            description = description.trim();

            determineTypeOfTask(tasks, typeOfTask, description);
        }
    }

    private static void determineTypeOfTask(Task[] tasks, String typeOfTask, String description) {
        switch (typeOfTask) {
        case "todo":
            createTodoTask(tasks, description);
            break;
        case "deadline":
            checkValidityOfDeadline(tasks, description);
            break;
        case "event":
            checkValidityOfEvent(tasks, description);
            break;
        case "done":
            checkValidityOfCompletedTask(tasks, description);
            break;
        default:
            printInvalidTaskMessage();
            break;
        }
    }

    private static void checkValidityOfDeadline(Task[] tasks, String description) {
        try {
            createDeadlineTask(tasks, description);
        } catch (DukeException e) {
            System.out.println("You need to let me know" + System.lineSeparator()
                    + "\"deadline <task name> /by <deadline details>\"");
            printSingleLine();
        }
    }

    private static void checkValidityOfEvent(Task[] tasks, String description) {
        try {
            createEventTask(tasks, description);
        } catch (DukeException e) {
            System.out.println("You need to let me know" + System.lineSeparator()
                    + "\"event <task name> /at <event details>\"");
            printSingleLine();
        }
    }

    private static void checkValidityOfCompletedTask(Task[] tasks, String description) {
        try {
            updateTaskAsComplete(tasks, description);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("I see no such task number. You're making me confused!");
            printSingleLine();
        }
    }

    private static void createTodoTask(Task[] tasks, String description) {
        Task newTask = new ToDo(description);
        tasks[Task.getNumberOfTask()] = newTask;
        printDetailsOfAddedTask(newTask);
    }

    private static void createDeadlineTask(Task[] tasks, String description) throws DukeException {
        if (!description.contains("/by")) {
            throw new DukeException();
        }
        String[] deadlineDetails = description.split("/by");
        String deadline = deadlineDetails[1].trim();
        Task newTask = new Deadline(deadlineDetails[0], deadline);
        tasks[Task.getNumberOfTask()] = newTask;
        printDetailsOfAddedTask(newTask);
    }

    private static void createEventTask(Task[] tasks, String description) throws DukeException {
        if (!description.contains("/at")) {
            throw new DukeException();
        }
        String[] eventDetails = description.split("/at");
        String eventDateTime = eventDetails[1].trim();
        Task newTask = new Event(eventDetails[0], eventDateTime);
        tasks[Task.getNumberOfTask()] = newTask;
        printDetailsOfAddedTask(newTask);
    }

    private static void printInvalidTaskMessage() {
        System.out.println("I'm sorry I don't understand you." + System.lineSeparator()
                + "Would you like to tell me again?");
        printSingleLine();
    }

    private static void printSingleLine() {
        System.out.println("_____________________________________________");
    }
}
