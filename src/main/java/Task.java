public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        if (isDone == true) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }
    public void markAsDone() {
        this.isDone = true;
    }
}
