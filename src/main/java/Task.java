public class Task {
    private final String description;
    private boolean isDone;
    private static int numberOfTask;

    private final String typeOfTask = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;

        numberOfTask++;
    }
    public String getDescription() {
        return description;
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
}
