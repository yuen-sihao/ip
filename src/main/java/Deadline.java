public class Deadline extends Task {

    private final String typeOfTask;
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        typeOfTask = "D";
    }

    @Override
    public String getTypeOfTask() {
        return typeOfTask;
    }

    @Override
    public String toString() {
        return "[" + getTypeOfTask() + "]" + super.toString() + "(by: " + deadline + ")";
    }
}
