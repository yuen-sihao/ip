package duke.task;

public class Task {
    private static int numberOfTask = 0;

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
        numberOfTask++;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public static int getNumberOfTask() {
        return numberOfTask;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
