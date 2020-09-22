package duke.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    private String typeOfTask;

    /**
     * Class constructor of the ToDo task.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
        typeOfTask = "T";
    }

    /**
     * Returns details of the ToDo task.
     *
     * @return Details.
     */
    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString();
    }
}
