import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

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