package pojos;

import java.util.ArrayList;

public class Aisle extends ArrayList<Slot> {

    int aisleNumber;
    ArrayList<Slot> slots;

    public Aisle(int aisleNumber, ArrayList<Slot> slots) {

        this.slots = slots;
        this.aisleNumber = aisleNumber;
    }
}
