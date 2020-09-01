public class ToDo extends Task {
    private final String typeOfTask;

    public ToDo(String description) {
        super(description);
        typeOfTask = "T";
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    @Override
    public String toString() {
        return "[" + getTypeOfTask() + "]" + super.toString();
    }
}
