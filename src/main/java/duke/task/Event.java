package duke.task;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private String typeOfTask;
    private String eventDateTime;

    /**
     * Class constructor of the Event task.
     *
     * @param description Description of the Event task.
     * @param eventDateTime Details of the Event task.
     */
    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
        typeOfTask = "E";
    }

    /**
     * Gets the details of this Event.
     *
     * @return Details.
     */
    public String getEventDateTime() {
        return eventDateTime;
    }

    /**
     * Returns details of the Event task.
     *
     * @return Details.
     */
    @Override
    public String toString() {
        return "[" + typeOfTask + "]" + super.toString() + "(at: " + eventDateTime + ")";
    }
}
