import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Planning {
    private TreeMap<LocalDate, Day> days = new TreeMap<>();
    private LocalDate startDay;
    private LocalDate endDay;

    public Planning(LocalDate startDay, LocalDate endDay, Calendar calendar) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public void setStartEndDay(LocalDate startDay, LocalDate endDay){
        this.startDay = startDay;
        this.endDay = endDay;
    }
    public LocalDate getStartDay(){
        return startDay;
    }
    public LocalDate getEndDay(){
        return endDay;
    }
    public Day getDay(LocalDate date) {
        // can return null if the day doesn't exist in the tree (days)
        return days.getOrDefault(date, null);
    }
    public void addDay(Day day) {
        days.putIfAbsent(day.getDate(), day);
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

    // temp
    public void showPlanning() {
        for (Map.Entry<LocalDate, Day> d : days.entrySet()) {
            System.out.println(d.getValue().getDate()+" ->  "+d);
        }
    }

}
