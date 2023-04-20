
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;


public class FreeZone implements Comparable<FreeZone> {
    private LocalTime startTime;
    private LocalTime endTime;

    public FreeZone(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }


    public Duration getDuration(){
        return Duration.between(startTime,endTime);
    }

    public ArrayList<FreeZone> appendTask(SimpleTask task, Duration minimumZonePeriod, LocalTime insertionTime){
        // we suppose that a task is insertable in the Zone (already checked)
        ArrayList<FreeZone> outPutZones = new ArrayList<FreeZone>(1);
        int splitBeforeIndicator = Duration.between(startTime,insertionTime).compareTo(minimumZonePeriod);
        int splitAfterIndicator =Duration.between(insertionTime.plus(task.getDuration()),endTime).compareTo(minimumZonePeriod);
        LocalTime newStartTime;
        LocalTime newEndTime;
        FreeZone zoneBefore;
        FreeZone zoneAfter;
        OccupiedZone occupiedZone;
        if (splitBeforeIndicator<0){
            newStartTime=startTime;
        }
        else {
            newStartTime=insertionTime;
            zoneBefore=new FreeZone(startTime,newStartTime);
            outPutZones.add(zoneBefore);
        }
        if (splitAfterIndicator<0) {
            newEndTime=endTime;
            occupiedZone=new OccupiedZone(newStartTime,newEndTime,task,task.getName());
            outPutZones.add(occupiedZone);
        }
        else {
            newEndTime=insertionTime.plus(task.getDuration());
            occupiedZone=new OccupiedZone(newStartTime,newEndTime,task,task.getName());
            outPutZones.add(occupiedZone);
            zoneAfter=new FreeZone(newEndTime,endTime);
            outPutZones.add(zoneAfter);
        }
        task.appendZone(occupiedZone);
        return outPutZones;
    }

    public ArrayList<FreeZone> appendTask(SimpleTask task, Duration minimumZonePeriod){
        return appendTask(task,minimumZonePeriod,startTime);
    }

    public ArrayList<FreeZone> appendTask(ComplexTask task,Duration minimumZonePeriod,LocalTime insertionTime,Duration subTaskDuration){
        // here we suppose that the task is unscheduled (not completly scheduled) and also insertable
        ArrayList<FreeZone> outPutZones = new ArrayList<FreeZone>(1);
        int splitBeforeIndicator = Duration.between(startTime,insertionTime).compareTo(minimumZonePeriod);
        int splitAfterIndicator =Duration.between(insertionTime.plus(subTaskDuration),endTime).compareTo(minimumZonePeriod);
        LocalTime newStartTime;
        LocalTime newEndTime;
        FreeZone zoneBefore;
        FreeZone zoneAfter;
        OccupiedZone occupiedZone;
        if (splitBeforeIndicator<0){
            newStartTime=startTime;
        }
        else {
            newStartTime=insertionTime;
            zoneBefore=new FreeZone(startTime,newStartTime);
            outPutZones.add(zoneBefore);
        }
        if (splitAfterIndicator<0) {
            newEndTime=endTime;
            occupiedZone=new OccupiedZone(newStartTime,newEndTime,task,task.getName()+" "+task.zonesNumber());
            outPutZones.add(occupiedZone);
        }
        else {
            newEndTime=insertionTime.plus(subTaskDuration);
            occupiedZone=new OccupiedZone(newStartTime,newEndTime,task,task.getName()+" "+task.zonesNumber());
            outPutZones.add(occupiedZone);
            zoneAfter=new FreeZone(newEndTime,endTime);
            outPutZones.add(zoneAfter);
        }
        task.appendZone(occupiedZone);
        return outPutZones;
    }
    public ArrayList<FreeZone> appendTask(ComplexTask task,Duration minimumZonePeriod,LocalTime insertionTime){
        Duration duration;
        if ( task.getDuration().compareTo(Duration.between(insertionTime,endTime))>=0 ){
            duration=Duration.between(insertionTime,endTime);
        }
        else{
            duration = task.getDuration();
        }
        return appendTask(task,minimumZonePeriod,insertionTime,duration);
    }

    public ArrayList<FreeZone> appendTask(ComplexTask task,Duration minimumZonePeriod,Duration duration){
        return appendTask(task,minimumZonePeriod,startTime,duration);
    }
    public ArrayList<FreeZone> appendTask(ComplexTask task,Duration minimumZonePeriod){
        Duration duration;
        if ( task.getDuration().compareTo(Duration.between(startTime,endTime))>=0 ){
            duration=Duration.between(startTime,endTime);
        }
        else{
            duration = task.getDuration();
        }
        return appendTask(task,minimumZonePeriod,startTime,duration);
    }

    public void showZone(){
        System.out.println("freeZone: from "+startTime.toString()+" to "+endTime.toString());

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
