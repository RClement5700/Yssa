package pojos;

import java.util.ArrayList;

public class Aisle extends ArrayList<Slot> {

    int aisleNumber;

    public Aisle(int aisleNumber) {
        this.aisleNumber = aisleNumber;
    }
}
