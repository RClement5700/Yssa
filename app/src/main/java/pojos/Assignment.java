package pojos;

import java.util.ArrayList;

public class Assignment {

    ArrayList<Slot> completedPicks;
    ArrayList<Slot> productsToPick;
    ArrayList<Slot> goBacks;
    ArrayList<Slot> previousPicks;

    public Assignment(ArrayList<Slot> productsToPick) {
        this.productsToPick = productsToPick;
    }
}
