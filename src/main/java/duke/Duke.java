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
    private static final String MESSAGE_TASK_CREATE = "One more thing you got to do. Press on!";
    private static final String MESSAGE_EMPTY_LIST = "Wow! I see that your list is empty";
    private static final String MESSAGE_LIST_HEADER = "Here are the tasks in your list:";
    private static final String MESSAGE_TASK_DONE = "Nice. One more down!";
    private static final String MESSAGE_TASK_DELETE = "A non-essential task ya? Ok removed!";
    private static final String MESSAGE_GOODBYE = "Bye. See you soon!";
    private static final String LINE_SPACING = "_____________________________________________";

    private static final String USER_COMMAND_LIST = "list";
    private static final String USER_COMMAND_TODO = "todo";
    private static final String USER_COMMAND_DEADLINE = "deadline";
    private static final String USER_COMMAND_EVENT = "event";
    private static final String USER_COMMAND_DELETE = "delete";
    private static final String USER_COMMAND_DONE = "done";
    private static final String USER_COMMAND_BYE = "bye";

    private static final String ERROR_INVALID_DEADLINE = "You need to let me know"
            + System.lineSeparator() + "\"deadline <task name> /by <deadline details>\"";
    private static final String ERROR_INVALID_EVENT = "You need to let me know"
            + System.lineSeparator() + "\"event <task name> /at <event details>\"";
    private static final String ERROR_INVALID_DELETE = "No such task to begin with!";
    private static final String ERROR_INVALID_TASK_NUMBER = "I see no such task number." +
            "You're making me confused!";
    private static final String ERROR_INVALID_TASK_TYPE = "I'm sorry I don't understand you."
            + System.lineSeparator() + "Would you like to tell me again?";
    private static final String ERROR_INVALID_IO = "I/O error has occurred";

    private static final String DATA_FILE_DIR = "data/";
    private static final String DATA_FILE = DATA_FILE_DIR + "/duke.txt";

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
            if (command.equals(USER_COMMAND_BYE)) {
                printGoodbyeMessage();
                isFinished = true;
            } else if (command.equals(USER_COMMAND_LIST)) {
                printListOfTask(tasks);
            } else {
                updateListOfTask(command, tasks);
                try {
                    saveListToFile(tasks, pathOfDataFile);
                } catch (IOException e) {
                    System.out.println(ERROR_INVALID_IO);
                }
            }
        }
    }

    private static String loadListFromFile(ArrayList<Task> tasks) {
        checkIfDirectoryExists();
        String pathOfDataFile = DATA_FILE;
        try {
            File dataFile = checkIfFileExists(pathOfDataFile);
            readListFromFile(tasks, dataFile);
        } catch (IOException e) {
            System.out.println(ERROR_INVALID_IO);
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
                System.out.println(ERROR_INVALID_TASK_TYPE);
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
        File directory = new File(DATA_FILE_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private static void saveListToFile(ArrayList<Task> tasks, String pathOfDataFile) throws IOException {
        writeDataToFile(pathOfDataFile, MESSAGE_LIST_HEADER
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
                System.out.println(ERROR_INVALID_TASK_TYPE);
                break;
            }
        }
    }

    private static void determineTypeOfTask(ArrayList<Task> tasks, String typeOfTask, String description) {
        switch (typeOfTask) {
        case USER_COMMAND_TODO:
            createTodoTask(tasks, description);
            break;
        case USER_COMMAND_DEADLINE:
            checkValidityOfDeadline(tasks, description);
            break;
        case USER_COMMAND_EVENT:
            checkValidityOfEvent(tasks, description);
            break;
        case USER_COMMAND_DONE:
            checkValidityOfCompletedTask(tasks, description);
            break;
        case USER_COMMAND_DELETE:
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
            System.out.println(ERROR_INVALID_DEADLINE);
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
            System.out.println(ERROR_INVALID_EVENT);
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
        System.out.println(MESSAGE_TASK_CREATE);
        System.out.println(newTask.toString());
        countNumberOfTask(tasks);
    }

    private static void checkValidityOfTaskToDelete(ArrayList<Task> tasks, String description) {
        try {
            deleteTaskFromList(tasks, description);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println(ERROR_INVALID_DELETE);
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
        System.out.println(MESSAGE_TASK_DELETE);
        System.out.println(tasksToDelete.toString());
        countNumberOfTask(tasks);
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
            System.out.println(MESSAGE_EMPTY_LIST);
        } else {
            System.out.println(MESSAGE_LIST_HEADER);
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
            System.out.println(ERROR_INVALID_TASK_NUMBER);
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
        System.out.println(MESSAGE_TASK_DONE);
        System.out.println(task.toString());
        printSingleLine();
    }

    private static void countNumberOfTask(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printSingleLine();
    }

    private static void printInvalidTaskMessage() {
        System.out.println(ERROR_INVALID_TASK_TYPE);
        printSingleLine();
    }

    private static void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
        printSingleLine();
        System.out.println(MESSAGE_GREETING);
        printSingleLine();
    }

    private static void printGoodbyeMessage() {
        System.out.println(MESSAGE_GOODBYE);
        printSingleLine();
    }

    private static void printSingleLine() {
        System.out.println(LINE_SPACING);
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
