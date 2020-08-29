public class ToDos extends Task {

    private final String typeOfTask;

    public ToDos (String description) {
        super(description);
        typeOfTask = "T";
    }

    @Override
    public String getTypeOfTask() {
        return typeOfTask;
    }
}
