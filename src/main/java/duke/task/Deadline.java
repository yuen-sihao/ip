package duke.task;

public class Deadline extends Task {
    private String typeOfTask;
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        typeOfTask = "D";
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString() + "(by: " + deadline + ")";
    }
}
