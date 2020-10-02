package pojos;

import java.sql.Time;
import java.util.ArrayList;

public class Assignment {

    private ArrayList<Slot> productsToPick;
    private ArrayList<Slot> completedPicks;
    private ArrayList<Slot> goBacks;
    private ArrayList<Slot> previousPicks;
    private int assignmentId;
    Time standardTime;
    Time startTime;
    Time finishTime;
    boolean isAssigned;

    public Assignment(ArrayList<Slot> productsToPick, int assignmentId, Time standardTime,
                      boolean isAssigned) {
        this.productsToPick = productsToPick;
        this.assignmentId = assignmentId;
        this.standardTime = standardTime;
        this.isAssigned = isAssigned;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public ArrayList<Slot> getCompletedPicks() {
        return completedPicks;
    }

    public ArrayList<Slot> getProductsToPick() {
        return productsToPick;
    }

    public ArrayList<Slot> getGoBacks() {
        return goBacks;
    }

    public ArrayList<Slot> getPreviousPicks() {
        return previousPicks;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }
}
