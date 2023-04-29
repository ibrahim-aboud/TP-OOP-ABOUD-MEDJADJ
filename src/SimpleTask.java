import java.time.Duration;
import java.time.LocalDateTime;

public class SimpleTask extends Task{

    public SimpleTask(String name, Category category, Priority priority, LocalDateTime deadLine, Duration duration,int dayPeriod){
        super(name, category, priority, deadLine, duration);
        this.dayPeriod=dayPeriod;
        this.assignedZone=null;
    }

    private int dayPeriod;
    private FreeZone assignedZone;
    @Override
    public void appendZone(FreeZone zone) {
        assignedZone=zone;
        setUnscheduled(false);
    }
    public void unAppendZone(){
        assignedZone=null;
        setUnscheduled(true);
    }

    public void showZone(){
        assignedZone.showZone();
    }

    public FreeZone getAssignedZone(){
        return assignedZone;
    }
}
