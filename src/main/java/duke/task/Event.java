package duke.task;

public class Event extends Task {
    private String typeOfTask;
    private String eventDateTime;

    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
        typeOfTask = "E";
    }

    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString() + "(at: " + eventDateTime + ")";
    }
}
