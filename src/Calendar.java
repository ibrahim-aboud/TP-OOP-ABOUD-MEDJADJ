import java.util.ArrayList;
import java.util.Collection;

public class Calendar {
    // each user is bonded with one calendar and vice-versa
    private User user;
    // a calendar should contains all plannings
    private ArrayList<Planning> plannings;
    // all days contained in all plannings should be grouped in one collection (did not decide the collection specefic child class yet)
    private Collection<Day> days;

    public void addPlanning(Planning planning){
        // make all the necessary checks before adding the planning using the ArrayList adding method (use the predefined "add" method)
    }
    public void removePlanning(Planning planning){
        // check whether the planning exists (using it's ID, and then removing it using the predefined method, must have a user validation before ...)
    }
    public void extendPlanning(Planning planning){
        //check first that the given planning is extendable, if so then call : planning.extendPlanning
    }
}
