package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
