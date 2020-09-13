package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printWelcomeMessage();
        ArrayList<Task> tasks = new ArrayList<>();
        String pathOfDataFile = loadListFromFile(tasks);
        String command;
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
                try {
                    saveListToFile(tasks, pathOfDataFile);
                } catch (IOException e) {
                    System.out.println("I/O error has occurred");
                }
            }
        }
    }

    private static String loadListFromFile(ArrayList<Task> tasks) {
        checkIfDirectoryExists();
        String pathOfDataFile = "data/duke.txt";
        try {
            File dataFile = checkIfFileExists(pathOfDataFile);
            readListFromFile(tasks, dataFile);
        } catch (IOException e) {
            System.out.println("I/O error has occurred");
        }

        if (tasks.size() > 0) {
            printListOfTask(tasks);
        }
        return pathOfDataFile;
    }

    private static void readListFromFile(ArrayList<Task> tasks, File dataFile) throws FileNotFoundException {
        Scanner fileReader = new Scanner(dataFile);
        if (fileReader.hasNextLine()) {
            fileReader.nextLine();
        }
        while (fileReader.hasNextLine()) {
            String[] sectionsOfCurrentLine;
            String currentLine = fileReader.nextLine();
            sectionsOfCurrentLine = currentLine.split("\\|");
            String typeOfSavedTask = sectionsOfCurrentLine[0].trim();
            String statusOfSavedTask = sectionsOfCurrentLine[1].trim();
            String informationOfSavedTask = sectionsOfCurrentLine[2].stripLeading();
            Task newTask;
            switch (typeOfSavedTask) {
            case "T":
                newTask = new ToDo(informationOfSavedTask);
                addTaskToList(tasks, statusOfSavedTask, newTask);
                break;
            case "D":
                String detailsOfSavedDeadline = sectionsOfCurrentLine[3].trim();
                newTask = new Deadline(informationOfSavedTask, detailsOfSavedDeadline);
                addTaskToList(tasks, statusOfSavedTask, newTask);
                break;
            case "E":
                String detailsOfSavedEvent = sectionsOfCurrentLine[3].trim();
                newTask = new Event(informationOfSavedTask, detailsOfSavedEvent);
                addTaskToList(tasks, statusOfSavedTask, newTask);
                break;
            default:
                System.out.println("Unknown task type");
                break;
            }
        }
    }

    private static void addTaskToList(ArrayList<Task> tasks, String statusOfSavedTask, Task newTask) {
        if (statusOfSavedTask.equals("1")) {
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

    private static File checkIfFileExists(String pathOfDataFile) throws IOException {
        File dataFile = new File(pathOfDataFile);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        return dataFile;
    }

    private static void checkIfDirectoryExists() {
        File directory = new File("data/");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private static void saveListToFile(ArrayList<Task> tasks, String pathOfDataFile) throws IOException {
        writeDataToFile(pathOfDataFile, "Here are the tasks in your list:"
                                    + System.lineSeparator());
        for (Task task : tasks) {
            String description = task.getDescription();
            String status;
            if (task.getStatusIcon().equals("\u2713")) {
                status = "1";
            } else {
                status = "0";
            }
            String typeOfSaveTask = task.getTypeOfTask();
            switch (typeOfSaveTask) {
            case "T":
                appendDataToFile(pathOfDataFile, typeOfSaveTask + " | "
                        + status + " | " + description + System.lineSeparator());
                break;
            case "D":
                Deadline deadlineToSave = (Deadline) task;
                String deadlineDetails = deadlineToSave.getDeadline();
                appendDataToFile(pathOfDataFile, typeOfSaveTask + " | "
                        + status + " | " + description + "| " + deadlineDetails + System.lineSeparator());
                break;
            case "E":
                Event eventToSave = (Event) task;
                String eventDetails = eventToSave.getEventDateTime();
                appendDataToFile(pathOfDataFile, typeOfSaveTask + " | "
                        + status + " | " + description + "| " + eventDetails + System.lineSeparator());
                break;
            default:
                System.out.println("Unknown task type");
                break;
            }
        }
    }

    private static void determineTypeOfTask(ArrayList<Task> tasks, String typeOfTask, String description) {
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
        case "delete":
            checkValidityOfTaskToDelete(tasks, description);
            break;
        default:
            printInvalidTaskMessage();
            break;
        }
    }

    private static void createTodoTask(ArrayList<Task> tasks, String description) {
        Task newTask = new ToDo(description);
        addNewTaskToList(tasks, newTask);
    }

    private static void checkValidityOfDeadline(ArrayList<Task> tasks, String description) {
        try {
            createDeadlineTask(tasks, description);
        } catch (DukeException e) {
            System.out.println("You need to let me know"
                    + System.lineSeparator() + "\"deadline <task name> /by <deadline details>\"");
            printSingleLine();
        }
    }

    private static void createDeadlineTask(ArrayList<Task> tasks, String description) throws DukeException {
        if (!description.contains("/by")) {
            throw new DukeException();
        }
        String[] deadlineDetails = description.split("/by");
        String deadline = deadlineDetails[1].trim();
        Task newTask = new Deadline(deadlineDetails[0], deadline);
        addNewTaskToList(tasks, newTask);
    }

    private static void checkValidityOfEvent(ArrayList<Task> tasks, String description) {
        try {
            createEventTask(tasks, description);
        } catch (DukeException e) {
            System.out.println("You need to let me know"
                    + System.lineSeparator() + "\"event <task name> /at <event details>\"");
            printSingleLine();
        }
    }

    private static void createEventTask(ArrayList<Task> tasks, String description) throws DukeException {
        if (!description.contains("/at")) {
            throw new DukeException();
        }
        String[] eventDetails = description.split("/at");
        String eventDateTime = eventDetails[1].trim();
        Task newTask = new Event(eventDetails[0], eventDateTime);
        addNewTaskToList(tasks, newTask);
    }

    private static void addNewTaskToList(ArrayList<Task> tasks, Task newTask) {
        tasks.add(newTask);
        printDetailsOfAddedTask(tasks, newTask);
    }

    private static void printDetailsOfAddedTask(ArrayList<Task> tasks, Task newTask) {
        System.out.println("One more thing you got to do. Press on!");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printSingleLine();
    }

    private static void checkValidityOfTaskToDelete(ArrayList<Task> tasks, String description) {
        try {
            deleteTaskFromList(tasks, description);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("No such task to begin with!");
            printSingleLine();
        }
    }

    private static void deleteTaskFromList(ArrayList<Task> tasks, String description) {
        int taskNumber = Integer.parseInt(description);
        int indexOfTask = taskNumber - 1;
        Task tasksToDelete = tasks.get(indexOfTask);
        tasks.remove(tasksToDelete);
        printDetailsOfDeletedTask(tasks, tasksToDelete);
    }

    private static void printDetailsOfDeletedTask(ArrayList<Task> tasks, Task tasksToDelete) {
        System.out.println("A non-essential task ya? Ok removed!");
        System.out.println(tasksToDelete.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printSingleLine();
    }

    private static void updateListOfTask(String command, ArrayList<Task> tasks) {
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

    private static void printListOfTask(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("Wow! I see that your list is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
        printSingleLine();
    }

    private static void checkValidityOfCompletedTask(ArrayList<Task> tasks, String description) {
        try {
            updateTaskAsComplete(tasks, description);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("I see no such task number. You're making me confused!");
            printSingleLine();
        }
    }

    private static void updateTaskAsComplete(ArrayList<Task> tasks, String description) {
        int taskNumber = Integer.parseInt(description);
        int indexOfTask = taskNumber - 1;
        tasks.get(indexOfTask).markAsDone();
        printDetailsOfCompletedTask(tasks.get(indexOfTask));
    }

    private static void printDetailsOfCompletedTask(Task task) {
        System.out.println("Nice. One more down!");
        System.out.println(task.toString());
        printSingleLine();
    }

    private static void printInvalidTaskMessage() {
        System.out.println("I'm sorry I don't understand you."
                + System.lineSeparator() + "Would you like to tell me again?");
        printSingleLine();
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

    private static void printSingleLine() {
        System.out.println("_____________________________________________");
    }

    private static void writeDataToFile(String pathOfFile, String dataToAdd) throws IOException {
        FileWriter fw = new FileWriter(pathOfFile);
        fw.write(dataToAdd);
        fw.close();
    }

    private static void appendDataToFile(String pathOfFile, String dataToAppend) throws IOException {
        FileWriter fw = new FileWriter(pathOfFile, true);
        fw.write(dataToAppend);
        fw.close();
    }
}
