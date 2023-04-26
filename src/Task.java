import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Date;

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

    // checks whether a task is insertable in a zone
    public boolean isInsertable(FreeZone zone){
        return isInsertable(zone,zone.getStartTime());
    }


    // must check that is unscheduled first
    // insertion time is within the zone --> Exception : TimeNotInZone
    // the zone is not occupied --> Exception : ZoneIsOccupied
    // the task fits inside the zone (taking into account the insertion time) --> Exception : NotFitInZone
    public boolean isInsertable(FreeZone zone,LocalTime insertionTime){
        //must check first that it is unscheduled !
        // verify wether the zone is after the deadline of the task and that the zone is not occupied
        if(zone.contains(insertionTime) && !(zone instanceof OccupiedZone) ){
            // verify if the task fits inside the zone
            if(getDuration().minus(Duration.between(insertionTime,zone.getEndTime())).isNegative() || getDuration().minus(Duration.between(insertionTime,zone.getEndTime())).isZero() ){
                return true;
            }
        }
        return false;
    }

    // the day respects the deadline of the task --> Exception : BeyondDeadLine
    // at least one of the zones can accept the task --> Exception : NotFitInDay
    public boolean isInsertable(Day day){
        if(getDeadLine().toLocalDate().equals(day.getDate()) || getDeadLine().toLocalDate().isAfter(day.getDate())){
            for(FreeZone zone: day.getZones()){
                if(isInsertable(zone)){
                    return true;
                }
            }
        }
        return false;
    }

    // checks whether a task is insertable in a day given a specefic zone
    public boolean isInsertable(Day day,FreeZone zone){
        return isInsertable(day,zone,zone.getStartTime());
    }


    // The day accepts the task --> Exception : (one of the two mentioned in isInsertable(Day))
    // The zone is within the day --> Exception : ZoneNotInDay
    // The zone accepts the task --> Exception : (one of the mentioned in isInsertable(zone,Day))
    // The zone respects the deadline of the task --> Exception : BeyondDeadLine
    public boolean isInsertable(Day day,FreeZone zone,LocalTime insertionTime){
        return day.contains(zone) && isInsertable(day) && isInsertable(zone) && (!getDeadLine().toLocalDate().equals(day.getDate())||(getDeadLine().toLocalTime().isAfter(insertionTime.plus(getDuration())) || getDeadLine().toLocalTime().equals(insertionTime.plus(getDuration()))) );
    }

    // at least one of the zones respects the time --> Exception : TimeNotInZones
    // any other exception should be at ( isInsertable(day,zn,insertionTime) )
    public boolean isInsertable(Day day,LocalTime insertionTime){
        for(FreeZone zn: day.getZones()){
            if(zn.contains(insertionTime)){
                return isInsertable(day,zn,insertionTime);
            }
        }
        return false;
    }


    // watch out ! this one can return a null value !
    // returns the first zone that you can insert a task inside (in a given day ofc)
    public FreeZone getInsertable(Day day){
        if(getDeadLine().toLocalDate().equals(day.getDate()) || getDeadLine().toLocalDate().isAfter(day.getDate())){
            for(FreeZone zn: day.getZones()){
                if(isInsertable(zn)){
                    return zn;
                }
            }
        }
        return null;
    }
    public boolean equals(Task task){
        return (this.unscheduled==task.getUnscheduled() && this.name==task.getName() && this.deadLine.equals(task.deadLine) && this.duration.minus(task.duration)==Duration.ZERO);
    }

}
