import java.util.Scanner;

public class Duke {
    public static final int MAX_NUMBER_OF_TASK = 100;

    public static void main(String[] args) {
        printWelcomeMessage();
        String command;
        Task[] tasks = new Task[MAX_NUMBER_OF_TASK];
        boolean isFinished = false;
        Scanner input = new Scanner(System.in);
        while (!isFinished) {
            if (!input.hasNextLine()) {
                break;
            }
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
        System.out.println("Hello! I'm Duke");
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
    }

    private static void createTodoTask(Task[] tasks, String description) {
        Task newTask = new ToDo(description);
        tasks[Task.getNumberOfTask()] = newTask;
        printDetailsOfAddedTask(newTask);
    }

    private static void createDeadlineTask(Task[] tasks, String description) {
        String[] deadlineDetails = description.split("/by");
        String deadline = deadlineDetails[1].trim();
        Task newTask = new Deadline(deadlineDetails[0], deadline);
        tasks[Task.getNumberOfTask()] = newTask;
        printDetailsOfAddedTask(newTask);
    }

    private static void createEventTask(Task[] tasks, String description) {
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
        System.out.println("___________________________________");
    }
}
