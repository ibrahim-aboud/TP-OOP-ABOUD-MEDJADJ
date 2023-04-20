import java.time.LocalTime;

public class OccupiedZone extends FreeZone{
    public OccupiedZone(LocalTime startTime,LocalTime endTime,Task task,String name){
        super(startTime,endTime);
        this.task=task;
        this.name=name;
    }
    private static final boolean free = false;
    private Task task;

    // it has the same name of the task if the task is simple, other wise (complex task) it would have the name of the task plus a number
    private String name;


    public boolean isfree(){
        return free;
    }
    public void showZone(){
        System.out.println("occupiedZone "+name+" : from "+getStartTime().toString()+"to"+getEndTime().toString());
    }
}
