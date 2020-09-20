package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DATA_FILE_DIR = "data/";
    private static final String DATA_FILE = DATA_FILE_DIR + "/duke.txt";

    private static final String ERROR_INVALID_IO = "I/O error has occurred";
    private static final String ERROR_INVALID_TASK_TYPE = "I'm sorry I don't understand you."
            + System.lineSeparator() + "Would you like to tell me again?";

    private static final String MESSAGE_LIST_HEADER = "Here are the tasks in your list:";

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

    public void loadListFromFile(ArrayList<Task> tasks) {
        try {
            File dataFile = new File(DATA_FILE);
            readListFromFile(tasks, dataFile);
        } catch (IOException e) {
            System.out.println(ERROR_INVALID_IO);
        }
        if (tasks.size() > 0) {
            Ui.printListOfTask(tasks);
        }
    }

    private void readListFromFile(ArrayList<Task> tasks, File dataFile) throws FileNotFoundException {
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

    private void addTaskToList(ArrayList<Task> tasks, String statusOfSavedTask, Task newTask) {
        if (statusOfSavedTask.equals("1")) {
            newTask.markAsDone();
        }
        tasks.add(newTask);
    }

    public static void saveListToFile(ArrayList<Task> tasks, String pathOfDataFile) throws IOException {
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
