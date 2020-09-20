package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {

    private static final String ERROR_INVALID_DEADLINE = "You need to let me know"
            + System.lineSeparator() + "\"deadline <task name> /by <deadline details>\"";
    private static final String ERROR_INVALID_EVENT = "You need to let me know"
            + System.lineSeparator() + "\"event <task name> /at <event details>\"";
    private static final String ERROR_INVALID_DELETE = "No such task to begin with!";
    private static final String ERROR_INVALID_TASK_NUMBER = "I see no such task number." +
            "You're making me confused!";

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void createTodoTask(ArrayList<Task> tasks, String description) {
        Task newTask = new ToDo(description);
        addNewTaskToList(tasks, newTask);
    }

    public static void checkValidityOfDeadline(ArrayList<Task> tasks, String description) {
        try {
            createDeadlineTask(tasks, description);
        } catch (DukeException e) {
            System.out.println(ERROR_INVALID_DEADLINE);
            Ui.printSingleLine();
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

    public static void checkValidityOfEvent(ArrayList<Task> tasks, String description) {
        try {
            createEventTask(tasks, description);
        } catch (DukeException e) {
            System.out.println(ERROR_INVALID_EVENT);
            Ui.printSingleLine();
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
        Ui.printDetailsOfAddedTask(tasks, newTask);
    }

    public static void checkValidityOfTaskToDelete(ArrayList<Task> tasks, String description) {
        try {
            deleteTaskFromList(tasks, description);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println(ERROR_INVALID_DELETE);
            Ui.printSingleLine();
        }
    }

    private static void deleteTaskFromList(ArrayList<Task> tasks, String description) {
        int taskNumber = Integer.parseInt(description);
        int indexOfTask = taskNumber - 1;
        Task tasksToDelete = tasks.get(indexOfTask);
        tasks.remove(tasksToDelete);
        Ui.printDetailsOfDeletedTask(tasks, tasksToDelete);
    }

    public static void checkValidityOfCompletedTask(ArrayList<Task> tasks, String description) {
        try {
            updateTaskAsComplete(tasks, description);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            System.out.println(ERROR_INVALID_TASK_NUMBER);
            Ui.printSingleLine();
        }
    }

    private static void updateTaskAsComplete(ArrayList<Task> tasks, String description) {
        int taskNumber = Integer.parseInt(description);
        int indexOfTask = taskNumber - 1;
        tasks.get(indexOfTask).markAsDone();
        Ui.printDetailsOfCompletedTask(tasks.get(indexOfTask));
    }
}
