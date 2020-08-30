import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASK = 100;

    public static void main(String[] args) {
        printWelcomeMessage();
        Task[] tasks = new Task[MAX_NUMBER_OF_TASK];
        boolean isFinished = false;
        while (!isFinished) {
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
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
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printSingleLine();
    }

    private static void updateListOfTask(String command, Task[] tasks) {
        int indexToSplit = command.indexOf(' ');
        String typeOfTask = command.substring(0, indexToSplit);
        String detailsOfTask = command.substring(indexToSplit);
        String description = detailsOfTask.trim();

        switch (typeOfTask) {
        case "todo":
            createTodoTask(tasks, description);
            break;
        case "deadline":
            createDeadlineTask(tasks, description);
            break;
        case "event":
            createEventTask(tasks, description);
            break;
        case "done":
            updateTaskAsComplete(tasks, description);
            break;
        default:
            printInvalidTaskMessage();
            break;
        }
    }

    private static void printInvalidTaskMessage() {
        printSingleLine();
        System.out.println("I'm sorry I don't understand you" + System.lineSeparator()
                + "Would you like to tell me again?");
        printSingleLine();
    }

    private static void updateTaskAsComplete(Task[] tasks, String description) {
        int taskNumber = Integer.parseInt(description);
        printDetailsOfCompletedTask(tasks[taskNumber]);
    }

    private static void createEventTask(Task[] tasks, String description) {
        Task newTask;
        String[] eventDetails = description.split("/at");
        String eventDateTime = eventDetails[1].trim();
        newTask = new Event(eventDetails[0], eventDateTime);
        tasks[Task.getNumberOfTask()] = newTask;
        printDetailsOfAddedTask(newTask);
    }

    private static void createDeadlineTask(Task[] tasks, String description) {
        Task newTask;
        String[] deadlineDetails = description.split("/by");
        String deadline = deadlineDetails[1].trim();
        newTask = new Deadline(deadlineDetails[0], deadline);
        tasks[Task.getNumberOfTask()] = newTask;
        printDetailsOfAddedTask(newTask);
    }

    private static void createTodoTask(Task[] tasks, String description) {
        Task newTask;
        newTask = new ToDo(description);
        tasks[Task.getNumberOfTask()] = newTask;
        printDetailsOfAddedTask(newTask);
    }

    private static void printDetailsOfCompletedTask(Task task) {
        printSingleLine();
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        printSingleLine();
    }

    private static void printDetailsOfAddedTask(Task newTask) {
        printSingleLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + Task.getNumberOfTask() + " tasks in the list.");
        printSingleLine();
    }

    private static void printListOfTask(Task[] tasks) {
        printSingleLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTask(); i++) {
            System.out.println((i + 1) + "." + tasks[(i + 1)].toString());
        }
        printSingleLine();
    }

    private static void printGoodbyeMessage() {
        printSingleLine();
        System.out.println("Bye. Hope to see you again soon!");
        printSingleLine();
    }

    private static void printSingleLine() {
        System.out.println("___________________________________");
    }
}
