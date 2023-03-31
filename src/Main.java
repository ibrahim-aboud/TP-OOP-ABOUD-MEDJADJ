import java.time.LocalDate;
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
        calendar.showCalendar();
        System.out.println(" ----------------");
        planning1.showPlanning();
    }
}