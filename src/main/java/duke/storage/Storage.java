package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    /** Directory and file that the list is saved to */
    private static final String DATA_FILE_DIR = "data/";
    private static final String DATA_FILE = DATA_FILE_DIR + "/duke.txt";

    /** Errors by this program */
    private static final String ERROR_INVALID_IO = "I/O error has occurred";
    private static final String ERROR_INVALID_TASK_TYPE = "I'm sorry I don't understand you."
            + System.lineSeparator() + "Would you like to tell me again?";

    private static final String MESSAGE_LIST_HEADER = "Here are the tasks in your list:";
    private static final String TICK_ICON = "\u2713";

    /**
     * Checks if the specified directory and file exists.
     * If the specified directory does not exists, directory is created.
     * If the specified file does not exists, file is created.
     *
     * @param pathOfDataFile File path to specify the directory and file.
     */
    public Storage(String pathOfDataFile) {
        File directory = new File(DATA_FILE_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File dataFile = new File(pathOfDataFile);
        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(ERROR_INVALID_IO);
        }
    }

    /**
     * Loads the list of tasks from the data file.
     *
     * @param tasks List that the data file is loaded to.
     */
    public void loadListFromFile(TaskList tasks) {
        try {
            File dataFile = new File(DATA_FILE);
            readListFromFile(tasks, dataFile);
        } catch (IOException e) {
            System.out.println(ERROR_INVALID_IO);
        }
        if (tasks.getTaskList().size() > 0) {
            Ui.printListOfTask(tasks);
        }
    }

    private void readListFromFile(TaskList tasks, File dataFile) throws FileNotFoundException {
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

    private void addTaskToList(TaskList tasks, String statusOfSavedTask, Task newTask) {
        if (statusOfSavedTask.equals("1")) {
            newTask.markAsDone();
        }
        tasks.getTaskList().add(newTask);
    }

    /**
     * Saves the list of tasks to the data file.
     *
     * @param tasks List that is saved to the data file.
     * @param pathOfDataFile File path to specify the directory and file.
     * @throws IOException If pathOfDataFile is not available.
     */
    public void saveListToFile(TaskList tasks, String pathOfDataFile) throws IOException {
        writeDataToFile(pathOfDataFile, MESSAGE_LIST_HEADER
                + System.lineSeparator());
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            String description = tasks.getTaskList().get(i).getDescription();
            String status;
            if (tasks.getTaskList().get(i).getStatusIcon().equals(TICK_ICON)) {
                status = "1";
            } else {
                status = "0";
            }

            Task taskToSave = tasks.getTaskList().get(i);
            if (taskToSave instanceof ToDo) {
                appendDataToFile(pathOfDataFile, "T | "
                        + status + " | " + description + System.lineSeparator());
            }
            else if (taskToSave instanceof Deadline) {
                Deadline deadlineToSave = (Deadline) tasks.getTaskList().get(i);
                String deadlineDetails = deadlineToSave.getDeadline();
                appendDataToFile(pathOfDataFile, "D | "
                        + status + " | " + description + "| " + deadlineDetails + System.lineSeparator());
            }
            else if (taskToSave instanceof Event) {
                Event eventToSave = (Event) tasks.getTaskList().get(i);
                String eventDetails = eventToSave.getEventDateTime();
                appendDataToFile(pathOfDataFile, "E | "
                        + status + " | " + description + "| " + eventDetails + System.lineSeparator());
            }
        }
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
