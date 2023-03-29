import java.time.LocalTime;

public class FreeZone {
    private static final boolean free = true;
    private LocalTime startTime;
    private LocalTime endTime;

    public boolean isFree(){
        return free;
    }
}
