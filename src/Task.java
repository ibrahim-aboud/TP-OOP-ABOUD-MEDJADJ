import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

abstract class Task implements Comparable<Task>{
    @Override
    public int compareTo(Task o) {
        // compares using 3 criterias : priority, deadline, and type (simple or decomposable)
        return 0;
    }
    private String name;
    private Category category;
    private Priority priority;
    private LocalDateTime deadLine;
    private Period period;
    private Boolean unscheduled;

    abstract public void appendZone(FreeZone zone);



}
