import java.time.LocalTime;

public class OccupiedZone extends FreeZone {
    private static final boolean free = false;
    private Task task;
    // it has the same name of the task if the task is simple, other wise (complex task) it would have the name of the task plus a number
    private String name;

    public OccupiedZone(LocalTime startTime, LocalTime endTime, String name) {
        super(startTime, endTime);
        this.name = name;
    }
}
