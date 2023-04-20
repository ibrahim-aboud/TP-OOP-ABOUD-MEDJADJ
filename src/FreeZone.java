import java.time.LocalTime;
import java.time.Duration;

public class FreeZone implements Comparable<FreeZone> {
    private static final boolean free = true;
    private LocalTime startTime;
    private LocalTime endTime;

    public FreeZone(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isFree(){
        return free;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }

    public Duration getDuration(){
        return Duration.between(startTime,endTime);
    }

    @Override
    public boolean equals(Object obj) {
        FreeZone z = (FreeZone) obj;
        return (z.getStartTime().equals(startTime) && z.getEndTime().equals(endTime));
    }

    @Override
    public int compareTo(FreeZone o) {
        return startTime.compareTo(o.getStartTime());
    }
}
