public class Task {
    private String description;
    private boolean isDone;
    private static int numberOfCommands;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

        this.numberOfCommands++;
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
    public static int getNumberOfCommands() {
        return numberOfCommands;
    }
    public void markAsDone() {
        this.isDone = true;
    }
}
