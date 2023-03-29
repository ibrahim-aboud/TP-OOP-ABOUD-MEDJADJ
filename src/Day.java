import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class Day {

    // add later a class that interprets the format a.m p.m instead of 24h format
    private static LocalTime[] timeInterval = {LocalTime.of(0,0),LocalTime.of(23,59)};
    private TreeSet<FreeZone> zones;
    // to tell which day of the week (SUNDAY, MONDAY, TUSDAY ...) or we can put a method that returns a DayOfWeek depending on
    private DayOfWeek dayOfWeek;
    private LocalDate date;
    public LocalDate getDate(){
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    // either separate the two add methods,
    public void addZone(FreeZone zone){}
    // remember to cast first !
    public void addZone(OccupiedZone zone){}
    public void removeZone(FreeZone zone){}
    public void emptyTasks(){}
    public void resetDay(){}
    public void addTask(Task task, LocalTime time){}
    public void addTask(Task task){}
}
