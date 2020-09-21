package duke.task;

public class ToDo extends Task {
    private String typeOfTask;

    public ToDo(String description) {
        super(description);
        typeOfTask = "T";
    }

    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString();
    }
}
