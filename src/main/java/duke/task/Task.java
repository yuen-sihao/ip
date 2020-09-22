package duke.task;

/**
 * Represents a Task in the list.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    private static final String TICK_ICON = "\u2713";
    private static final String CROSS_ICON = "\u2718";

    /**
     * Class constructor of Task.
     *
     * @param description Description of the specified task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns status icon of the specified task.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        if (isDone) {
            return TICK_ICON;
        } else {
            return CROSS_ICON;
        }
    }

    /**
     * Sets status of the specified task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns description of the specified task.
     *
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns details of the specified task.
     *
     * @return Details.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
