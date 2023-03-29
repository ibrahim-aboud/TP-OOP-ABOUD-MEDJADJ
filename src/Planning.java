import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Planning {
    private ArrayList<Day> days;
    private Date startDay;
    private Date endDay;

    public void setStartDay(Date startDay){
        this.startDay=startDay;
    }
    public void setEndDay(Date endDay){
        this.endDay=endDay;
    }
    public Date getStartDay(){
        return startDay;
    }
    public Date getEndDay(){
        return endDay;
    }
    public void extendPlanning(){
        // checks if extension is possible in the method extendPlanning of Calendar, after confirmation the extension operation is done here
    }
    public void addTask(Task task, Day day){
        // it will eventually call addTask of the class Day
    }
    public void addTask(Task task, LocalDateTime dateTime, Day day){
        // it will eventually call addTask of the class Day
    }

}
