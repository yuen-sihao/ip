package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {

    private static final String ERROR_INVALID_DEADLINE = "You need to let me know"
            + System.lineSeparator() + "\"deadline <task name> /by <deadline details>\"";
    private static final String ERROR_INVALID_EVENT = "You need to let me know"
            + System.lineSeparator() + "\"event <task name> /at <event details>\"";
    private static final String ERROR_INVALID_DELETE = "No such task to begin with!";
    private static final String ERROR_INVALID_TASK_NUMBER = "I see no such task number." +
            "You're making me confused!";

    private static final String FORMAT_DATE = "dd MMM yyyy";

    private static final String DELIMITER_BY = "/by";
    private static final String DELIMITER_AT = "/at";

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void createTodoTask(TaskList tasks, String description) {
        Task newTask = new ToDo(description);
        addNewTaskToList(tasks, newTask);
    }

    public static void checkValidityOfDeadline(TaskList tasks, String description) {
        try {
            createDeadlineTask(tasks, description);
        } catch (DukeException e) {
            System.out.println(ERROR_INVALID_DEADLINE);
            Ui.printSingleLine();
        }
    }

    private static void createDeadlineTask(TaskList tasks, String description) throws DukeException {
        if (!description.contains(DELIMITER_BY)) {
            throw new DukeException();
        }
        String[] deadlineDetails = description.split(DELIMITER_BY);
        String deadline = deadlineDetails[1].trim();

        try {
            LocalDate date = LocalDate.parse(deadline);
            deadline = date.format(DateTimeFormatter.ofPattern(FORMAT_DATE));
        } catch (java.time.format.DateTimeParseException e) {
            //deadline given is not a date
        }

        Task newTask = new Deadline(deadlineDetails[0], deadline);
        addNewTaskToList(tasks, newTask);
    }

    public static void checkValidityOfEvent(TaskList tasks, String description) {
        try {
            createEventTask(tasks, description);
        } catch (DukeException e) {
            System.out.println(ERROR_INVALID_EVENT);
            Ui.printSingleLine();
        }
    }

    private static void createEventTask(TaskList tasks, String description) throws DukeException {
        if (!description.contains(DELIMITER_AT)) {
            throw new DukeException();
        }
        String[] eventDetails = description.split(DELIMITER_AT);
        String eventDateTime = eventDetails[1].trim();

        try {
            LocalDate date = LocalDate.parse(eventDateTime);
            eventDateTime = date.format(DateTimeFormatter.ofPattern(FORMAT_DATE));
        } catch (java.time.format.DateTimeParseException e) {
            //eventDateTime given is not a date
        }

        Task newTask = new Event(eventDetails[0], eventDateTime);
        addNewTaskToList(tasks, newTask);
    }

    private static void addNewTaskToList(TaskList tasks, Task newTask) {
        tasks.getTaskList().add(newTask);
        Ui.printDetailsOfAddedTask(tasks, newTask);
    }

    public static void checkValidityOfTaskToDelete(TaskList tasks, String description) {
        try {
            deleteTaskFromList(tasks, description);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println(ERROR_INVALID_DELETE);
            Ui.printSingleLine();
        }
    }

    private static void deleteTaskFromList(TaskList tasks, String description) {
        int taskNumber = Integer.parseInt(description);
        int indexOfTask = taskNumber - 1;
        Task tasksToDelete = tasks.getTaskList().get(indexOfTask);
        tasks.getTaskList().remove(tasksToDelete);
        Ui.printDetailsOfDeletedTask(tasks, tasksToDelete);
    }

    public static void checkValidityOfCompletedTask(TaskList tasks, String description) {
        try {
            updateTaskAsComplete(tasks, description);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println(ERROR_INVALID_TASK_NUMBER);
            Ui.printSingleLine();
        }
    }

    private static void updateTaskAsComplete(TaskList tasks, String description) {
        int taskNumber = Integer.parseInt(description);
        int indexOfTask = taskNumber - 1;
        tasks.getTaskList().get(indexOfTask).markAsDone();
        Ui.printDetailsOfCompletedTask(tasks.getTaskList().get(indexOfTask));
    }
}
