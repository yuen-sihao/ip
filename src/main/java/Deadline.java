public class Deadline extends Task {
    private String typeOfTask;
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        typeOfTask = "D";
    }

    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString() + "(by: " + deadline + ")";
    }
}
