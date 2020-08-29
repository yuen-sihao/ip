public class Events extends Task {
    private final String typeOfTask;

    public Events (String description) {
        //TODO HERE
        super(description);
        typeOfTask = "E";
    }

    @Override
    public String getTypeOfTask() {
        return typeOfTask;
    }
}
