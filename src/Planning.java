import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Planning {
    private Set<Day> days = new TreeSet<>();
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

    public void addDay(Day day) {
        days.add(day);
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
        for (Day d : days) {
            System.out.println(d.getDate()+" ->  "+d);
        }
    }

}
