import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

abstract class Task implements Comparable<Task>{

    public Task(String name,Category category,Priority priority,LocalDateTime deadLine,Duration duration){
        this.name=name;
        this.category=category;
        this.priority=priority;
        this.deadLine=deadLine;
        this.duration=duration;
        this.unscheduled = true;
    }

    /**
     * helps interpreting the type of comparison and translating it to an index (see use in compareTo overloading)
     */
    private static String[] criteria = {"deadLine","priority","duration"};
    private String name;
    private Category category;
    private Priority priority;
    private LocalDateTime deadLine;
    private Duration duration;
    private Boolean unscheduled;



    public Boolean getUnscheduled() {
        return unscheduled;
    }

    public void setUnscheduled(Boolean unscheduled) {
        this.unscheduled = unscheduled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    abstract public void appendZone(FreeZone zone);

    /**
     * Default task comparing method (by deadline)
     * @param o the task to be compared with.
     * @return int : positive value : current is prior to o
     *               negative value : o is prior to current
     *               null value (0) : current is as important as o
     */
    @Override
    public int compareTo(Task o) {
        return -this.deadLine.compareTo(o.getDeadLine());
    }

    /**
     *
     * @param o the task to be compared with
     * @param i the criteria index (refer to the static array 'criteria')
     * @return int : positive value : current is prior to/takes more time than o
     *               negative value : o is prior to/takes more time than current
     *               null value (0) : current is as important as o
     */
    public int compareTo(Task o, int i){
        return switch (i) {
            case 0 -> compareTo(o);
            case 1 -> priority.compareTo(o.getPriority());
            case 2 -> duration.compareTo(o.getDuration());
            // add more cases here when new criteria are added to the static criteria array
            default -> 0;
        };
    }




}

