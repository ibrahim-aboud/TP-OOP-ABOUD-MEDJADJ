import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

public class Day implements Comparable<Day> {
    private static LocalTime[] timeInterval = {LocalTime.of(0, 0), LocalTime.of(23, 59)};
    private TreeSet<FreeZone> zones = new TreeSet<>(); // insertion et suppression de zones
    private DayOfWeek dayOfWeek;
    private LocalDate date;

    public Day(LocalDate date) {
        dayOfWeek = date.getDayOfWeek();
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return Objects.equals(date, day.date);
    }
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public int compareTo(Day o) {
        return date.compareTo(o.date);
    }
    public void insertZone(FreeZone zone) {
        LocalTime start = zone.getStartTime();
        LocalTime end = zone.getEndTime();

        if (start.isAfter(end)) System.out.println("ERREUR: Debut apres Fin?");
        else {
            boolean stop = false;
            FreeZone z;
            Iterator<FreeZone> iterator = zones.iterator();
            while (iterator.hasNext() && !stop) {
                z = iterator.next();
                if ((start.isAfter(z.getStartTime()) && start.isBefore(z.getEndTime())) || (end.isAfter(z.getStartTime()) && end.isBefore(z.getEndTime())) || (end.isAfter(z.getEndTime()) && start.isBefore(z.getStartTime()))) {
                    stop = true;
                }
            }
            if (!stop) {
                zones.add(zone);
            }
            else System.out.println("ERREUR: insertion impossible!");
        }
    }
    public void removeZone(FreeZone zone) {
        LocalTime start = zone.getStartTime();
        LocalTime end = zone.getEndTime();

        if (!zones.contains(zone)) System.out.println("ERREUR: Zone inexistante!");
        else {
            zones.remove(zone);
        }
    }

    public void showDay() {
        for (FreeZone z : zones) {
            System.out.println("->  "+z.getStartTime()+" - "+z.getEndTime());
            if(z instanceof OccupiedZone){
                System.out.println(((OccupiedZone) z).getTask().getName());
                System.out.println(((OccupiedZone) z).getTask().getDuration().toString());
            }

        }
    }

    public int getZonesNumber(){
        return zones.size();
    }
    public TreeSet<FreeZone> getZones(){
        return zones;
    }
    public boolean contains(FreeZone zone){
        return zones.contains(zone);
    }

    public boolean unAppendTask(Task task,FreeZone zone){

        FreeZone newZone;

        if(zone!=null){

            if(contains(zone) && (zone instanceof OccupiedZone)){

                if(((OccupiedZone) zone).contains(task)){
                    newZone = new FreeZone(zone.getStartTime(),zone.getEndTime());
                    removeZone(zone);
                    insertZone(newZone);
                    if(task instanceof SimpleTask){
                        ((SimpleTask) task).unAppendZone();
                    }
                    else{

                        ((ComplexTask)task).unAppend(zone);

                    }
                    return true;
                }
            }
        }
        return false;

    }

    // removes a task from a day (the one that we will use)
    // if it 
    public boolean unAppendTask(Task task){
        if(task instanceof SimpleTask){
            if(!task.getUnscheduled()){
                return unAppendTask(task,((SimpleTask) task).getAssignedZone());
            }

        }
        else if(task instanceof ComplexTask){
            boolean returnValue = false;
            ArrayList<FreeZone> currentZonesInTask=(ArrayList<FreeZone>)((ComplexTask) task).getAssignedZones().clone();
            for (FreeZone zone : currentZonesInTask) {
                if(!((ComplexTask) task).isTotallyFree()){
                    // Note: do not flip the two parameters
                    // because in boolean operations (logical 'or')
                    // if the first operator is true, no need to go
                    // through the other operators
                    // and so it can skip function execution
                    // which can be sometimes usefull
                    returnValue = unAppendTask(task,zone) || returnValue;

                }
            }
            return returnValue;
        }
        return false;
    }

    //For each of the following methods
    //you must check that the given task is unscheduled (because of other treatments with periodic tasks)

    // adds a task where possible in a day
    public boolean appendTask(Task task, Duration minimumZoneLength){
        // must check if unschedule before calling this
        if(task.isInsertable(this)){
            if(task instanceof SimpleTask){
                FreeZone zone=task.getInsertable(this);
                if(zone!=null){
                    removeZone(zone);
                    ArrayList<FreeZone> zones=zone.appendTask((SimpleTask)task,minimumZoneLength);
                    for (FreeZone zn : zones){
                        insertZone(zn);
                    }
                    return true;
                }
                return false;
            }
            else if(task instanceof ComplexTask){
                FreeZone zone=task.getInsertable(this);
                if(zone!=null){
                    removeZone(zone);
                    ArrayList<FreeZone> zones=zone.appendTask((ComplexTask)task,minimumZoneLength);
                    for(FreeZone zn:zones){
                        insertZone(zn);
                    }
                    return true;
                }
                return false;
            }
        }
        return false;

    }

    // adds a task to a day while specifying exactly the insertion time
    public boolean appendTask(Task task,Duration minimumZoneLength ,LocalTime insertionTime){
        if(task instanceof SimpleTask){
            FreeZone zone=task.getInsertable(this,insertionTime);
            if(zone!=null) {
                removeZone(zone);
                ArrayList<FreeZone> zones = zone.appendTask((SimpleTask) task, minimumZoneLength, insertionTime);
                for (FreeZone zn : zones) {
                    insertZone(zn);
                }
                return true;
            }
            return false;
        }
        else if(task instanceof ComplexTask){
            FreeZone zone=task.getInsertable(this,insertionTime);
            if(zone!=null){
                removeZone(zone);
                ArrayList<FreeZone> zones=zone.appendTask((ComplexTask)task,minimumZoneLength,insertionTime);
                for(FreeZone zn:zones){
                    insertZone(zn);
                }
                return true;
            }
            return false;
        }
        return false;
    }


    // adds a task to a day depending on the insertion time "like the previous one" and the duration
    // of the portion of the task you want to insert (if the task was complex, other wise the subtask
    // duration would not have any effect)
    public boolean appendTask(Task task,Duration minimumZoneLength ,LocalTime insertionTime,Duration subTaskDuration){
        if(task instanceof SimpleTask){
            FreeZone zone=task.getInsertable(this,insertionTime);
            if(zone!=null){
                removeZone(zone);
                ArrayList<FreeZone> zones=zone.appendTask((SimpleTask)task,minimumZoneLength);
                for (FreeZone zn : zones) {
                    insertZone(zn);
                }
                return true;
            }
            return false;

        }
        else if(task instanceof ComplexTask){
            FreeZone zone=task.getInsertable(this,insertionTime);
            if(zone!=null){
                removeZone(zone);
                ArrayList<FreeZone> zones=zone.appendTask((ComplexTask)task,minimumZoneLength,subTaskDuration);
                for(FreeZone zn:zones){
                    insertZone(zn);
                }
                return true;
            }
            return false;
        }
        return false;
    }


    // adds a task in a day when possible with specifying the duration of the portion inserted
    // this is when the task is complex, other wise that parameter is ignored
    public boolean appendTask(Task task,Duration minimumZoneLength ,Duration subTaskDuration){
        if(task instanceof SimpleTask){
            FreeZone zone=task.getInsertable(this);
            if(zone!=null){
                removeZone(zone);
                ArrayList<FreeZone> zones=zone.appendTask((SimpleTask)task,minimumZoneLength);
                for (FreeZone zn : zones) {
                    insertZone(zn);
                }
                return true;
            }
            return false;

        }
        else if(task instanceof ComplexTask){
            FreeZone zone=task.getInsertable(this);
            if(zone!=null){
                removeZone(zone);
                ArrayList<FreeZone> zones=zone.appendTask((ComplexTask)task,minimumZoneLength,subTaskDuration);
                for(FreeZone zn:zones){
                    insertZone(zn);
                }
                return true;
            }
            return false;
        }
        return false;
    }


    // inserting a task in a day in a specified zone (i don't really know the reason why but we may use it who knows)
    public boolean appendTask(Task task,Duration minimumZoneLength,FreeZone zone){
        return appendTask(task,minimumZoneLength,zone.getStartTime());
    }
}