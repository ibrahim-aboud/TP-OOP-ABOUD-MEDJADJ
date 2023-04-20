import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Calendar {
    // each user is bonded with one calendar and vice-versa
    private User user;
    // a calendar should contains all plannings
    private ArrayList<Planning> plannings = new ArrayList<>();
    private TreeMap<LocalDate, Day> calendar = new TreeMap<>();

    // add a structure for tasks (scheduled and unscheduled)


    public void addDay(Day day) {
        calendar.putIfAbsent(day.getDate(), day);
    }
    public Day getDay(LocalDate date) {
        // can return null if the day doesn't exist in the tree (days)
        return calendar.getOrDefault(date, null);
    }
    public void addPlanning(Planning planning) {
        Day day;
        // plannings can override, so there are no restriction to how plannings are added
        if (!planning.getStartDay().isBefore(LocalDate.now()) && !planning.getEndDay().isBefore(planning.getStartDay())) {
            for (LocalDate date = planning.getStartDay(); !date.isAfter(planning.getEndDay()); date = date.plusDays(1)) {
                if (!calendar.containsKey(date)) {
                    day = new Day(date);
                    planning.addDay(day);
                    addDay(day); //  add to the calendar
                }
                else planning.addDay(calendar.get(date));
            }
            plannings.add(planning);

        }
        else System.out.println("ERREUR: Période de planning invalide!");
    }
    public void removePlanning(Planning planning) {
        // check whether the planning exists (using it's ID, and then removing it using the predefined method, must have a user validation before ...)
    }
    public void extendPlanning(Planning planning) {
        //check first that the given planning is extendable, if so then call : planning.extendPlanning
    }

    //temp
    public void showCalendar() {
        for (Map.Entry<LocalDate, Day> entry : calendar.entrySet()) {
            System.out.println(entry.getKey() + " ->  " + entry.getValue());
        }
    }
}
