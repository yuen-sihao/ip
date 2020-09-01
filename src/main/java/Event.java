public class Event extends Task {
    private final String typeOfTask;
    private final String eventDateTime;

    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
        typeOfTask = "E";
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    @Override
    public String toString() {
        return "[" + getTypeOfTask() + "]" + super.toString() + "(at: " + eventDateTime + ")";
    }
}
