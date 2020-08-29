import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWelcomeMessage();
        String command;
        Task[] tasks = new Task[100];
        boolean isFinished = false;
        while (!isFinished) {
            Scanner input = new Scanner(System.in);
            command = input.nextLine();
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
        case "todo": {
            Task newTask = new ToDos(description);
            tasks[Task.getNumberOfTask()] = newTask;
            printDetailsOfAddedTask(newTask);
            break;
        }
        case "deadline": {
            String[] deadlineDetails = description.split("/by");
            String deadline = deadlineDetails[1].trim();
            String printedDescription = deadlineDetails[0] + "(by: " + deadline + ")";
            Task newTask = new Deadlines(printedDescription);
            tasks[Task.getNumberOfTask()] = newTask;
            printDetailsOfAddedTask(newTask);
            break;
        }
        case "event": {
            String[] eventDetails = description.split("/at");
            String eventDateTime = eventDetails[1].trim();
            String printedDescription = eventDetails[0] + "(at: " + eventDateTime + ")";
            Task newTask = new Events(printedDescription);
            tasks[Task.getNumberOfTask()] = newTask;
            printDetailsOfAddedTask(newTask);
            break;
        }
        case "done":
            int taskNumber = Integer.parseInt(description);
            printDetailsOfCompletedTask(tasks[taskNumber]);
            break;
        }
    }

    private static void printDetailsOfCompletedTask(Task task) {
        printSingleLine();
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] "
                + task.getDescription());
        printSingleLine();
    }

    private static void printDetailsOfAddedTask(Task newTask) {
        printSingleLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("[" + newTask.getTypeOfTask() + "]"
                + "[" + newTask.getStatusIcon() + "] "
                + newTask.getDescription());
        System.out.println("Now you have " + Task.getNumberOfTask() + " tasks in the list.");
        printSingleLine();
    }

    private static void printListOfTask(Task[] tasks) {
        printSingleLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.getNumberOfTask(); i++) {
            System.out.println((i + 1) + ".[" + tasks[i + 1].getTypeOfTask()
                    + "][" + tasks[i + 1].getStatusIcon() + "] "
                    + tasks[i + 1].getDescription());
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
