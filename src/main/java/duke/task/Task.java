package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    private static final String TICK_ICON = "\u2713";
    private static final String CROSS_ICON = "\u2718";

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return TICK_ICON;
        } else {
            return CROSS_ICON;
        }
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
