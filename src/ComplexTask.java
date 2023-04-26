import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ComplexTask extends Task{
    public ComplexTask(String name, Category category, Priority priority, LocalDateTime deadLine, Duration duration){
        super(name,category,priority,deadLine,duration);
        this.assignedZones=new ArrayList<FreeZone>(0);
    }
    private ArrayList<FreeZone> assignedZones;
    @Override
    public void appendZone(FreeZone zone) {
        assignedZones.add(zone);
        if (getDuration().compareTo(Duration.ZERO)<=0){
            setUnscheduled(false);
        }
    }

    public boolean unAppend(FreeZone zone){
        if(assignedZones.contains(zone)){
            assignedZones.remove(zone);
            setUnscheduled(true);
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public Duration getDuration() {
        Duration durationLeft = super.getDuration();
        if(!assignedZones.isEmpty()){
            for (Iterator<FreeZone> iter = assignedZones.iterator(); iter.hasNext(); ) {
                durationLeft = durationLeft.minus(iter.next().getDuration());
            }
        }

        return durationLeft;
    }

    public int zonesNumber(){
        return assignedZones.size();
    }

    public void showZone(){
        for (Iterator<FreeZone> iter = assignedZones.iterator(); iter.hasNext(); ) {
            iter.next().showZone();
        }
    }

    // Goto Task for full explanation of exceptions
    public boolean isInsertable(FreeZone zone, LocalTime insertionTime){
        //must check first that it is unscheduled !
        // verify wether the zone is after the deadline of the task and that the zone is not occupied
        if(zone.contains(insertionTime) && !(zone instanceof OccupiedZone) ){
            // no need to check if the task fits in the zone since it will decompose anyways
            return true;

        }
        return false;
    }
}
