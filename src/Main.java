import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.time.LocalTime;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        // We need a way to store user info on the machine, eg. a file.
//        app.signup("ok", "123");
//        app.login("ok", "123");

        Calendar calendar = new Calendar();
        calendar.addDay(new Day(LocalDate.now()));
        calendar.addDay(new Day(LocalDate.of(1990, 12, 12)));
        calendar.addDay(new Day(LocalDate.of(1995, 12, 12)));
        calendar.addDay(new Day(LocalDate.of(2023, 4, 12)));
        calendar.addDay(new Day(LocalDate.of(2023, 4, 13)));
        calendar.addDay(new Day(LocalDate.of(2023, 4, 14)));
        Planning planning1 = new Planning(LocalDate.now(), LocalDate.of(2023, 4, 23), calendar);

        calendar.addPlanning(planning1);

//        calendar.showCalendar();
//        System.out.println(" ----------------");
//        planning1.showPlanning();

        Category category = new Category("yes","infrared");

//        Task task1 = new SimpleTask("prepareTools",category,Priority.HIGH, LocalDateTime.of(2023,12,12,17,30), Duration.ofHours(3),1);
//        Task task2 = new ComplexTask("prepareTools",category,Priority.HIGH, LocalDateTime.of(2023,12,12,17,30), Duration.ofHours(5));
//        FreeZone freeZone= new FreeZone(LocalTime.of(8,30),LocalTime.of(11,30));
//        FreeZone freeZone2= new FreeZone(LocalTime.of(8,30),LocalTime.of(16,0));
//        freeZone.showZone();
//        ArrayList<FreeZone> fragementedZones=freeZone.appendTask((SimpleTask) task1,Duration.ofMinutes(30));
//        for (Iterator<FreeZone> iter = fragementedZones.iterator(); iter.hasNext(); ) {
//            iter.next().showZone();
//        }
//        ((SimpleTask) task1).showZone();
        /*ArrayList<FreeZone> fragementedZones=freeZone.appendTask((ComplexTask) task2,Duration.ofMinutes(30),LocalTime.of(14,0));
        for (Iterator<FreeZone> iter = fragementedZones.iterator(); iter.hasNext(); ) {
            iter.next().showZone();
        }
        ((ComplexTask) task2).showZone();
        System.out.println(" ");
        ArrayList<FreeZone> fragementedZones2=freeZone2.appendTask((ComplexTask) task2,Duration.ofMinutes(30),LocalTime.of(8,30));
        for (Iterator<FreeZone> iter = fragementedZones2.iterator(); iter.hasNext(); ) {
            iter.next().showZone();
        }
        ((ComplexTask) task2).showZone();*/
//        calendar.showCalendar();
//        System.out.println(" ----------------");
//        planning1.showPlanning();

        FreeZone zone1 = new FreeZone(LocalTime.of(18, 30), LocalTime.of(18, 59));
        FreeZone zone2 = new FreeZone(LocalTime.of(8, 30), LocalTime.of(10, 59));
        FreeZone zone3 = new FreeZone(LocalTime.of(1, 30), LocalTime.of(1, 59));
        FreeZone zone4 = new FreeZone(LocalTime.of(20, 30), LocalTime.of(22, 59));
        FreeZone zone5 = new FreeZone(LocalTime.of(7, 30), LocalTime.of(8, 30));

        calendar.getDay(LocalDate.of(1990, 12, 12)).insertZone(zone1);
        calendar.getDay(LocalDate.of(1990, 12, 12)).insertZone(zone2);
        calendar.getDay(LocalDate.of(1990, 12, 12)).insertZone(zone3);
        calendar.getDay(LocalDate.of(1990, 12, 12)).insertZone(zone4);
        calendar.getDay(LocalDate.of(1990, 12, 12)).insertZone(zone5);

        calendar.getDay(LocalDate.of(1990, 12, 12)).removeZone(new FreeZone(LocalTime.of(1, 30), LocalTime.of(1, 59)));

        calendar.getDay(LocalDate.of(1990, 12, 12)).showDay();

    }
}