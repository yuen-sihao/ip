package duke.task;

public class ToDo extends Task {
    private String typeOfTask;

    public ToDo(String description) {
        super(description);
        typeOfTask = "T";
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString();
    }
}
