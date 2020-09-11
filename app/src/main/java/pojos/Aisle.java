package pojos;

import java.util.ArrayList;

public class Aisle extends ArrayList<Slot> {

    boolean isAscending;
    int aisleNumber;
    ArrayList<Slot> slots;

    public Aisle(int aisleNumber, ArrayList<Slot> slots) {

        this.slots = slots;
        this.aisleNumber = aisleNumber;
    }

    public void setAscending(boolean ascending) {
        isAscending = ascending;
    }
}
