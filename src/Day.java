import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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
        }
    }
}