package duke.task;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private String typeOfTask;
    private String deadline;

    /**
     * Class constructor of the Deadline task.
     *
     * @param description Description of the Deadline task.
     * @param deadline Details of the Deadline task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        typeOfTask = "D";
    }

    /**
     * Gets the details of this Deadline.
     *
     * @return Details.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Returns details of the Deadline task.
     *
     * @return Details.
     */
    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString() + "(by: " + deadline + ")";
    }
}
