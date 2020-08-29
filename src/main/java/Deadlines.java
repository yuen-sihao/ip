public class Deadlines extends Task {

    private final String typeOfTask;

    public Deadlines (String description) {
        //TODO HERE
        super(description);
        typeOfTask = "D";
    }

    @Override
    public String getTypeOfTask() {
        return typeOfTask;
    }
}
