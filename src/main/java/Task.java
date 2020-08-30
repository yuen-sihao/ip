public class Task {
    private static int numberOfTask;

    private final String description;
    private boolean isDone;
    private final String typeOfTask = "";

    public Task(String description) {
        this.description = description;
        isDone = false;
        numberOfTask++;
    }

    public String getStatusIcon() {
        if (isDone == true) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public static int getNumberOfTask() {
        return numberOfTask;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
