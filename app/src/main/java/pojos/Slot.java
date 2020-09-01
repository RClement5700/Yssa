package pojos;

import java.util.HashMap;

/*
    key     = position ID
    value   = check digit
 */
public class Slot extends HashMap<Integer, Integer>
{
    String productDescription;

    public Slot(String productDescription) {
        this.productDescription = productDescription;

    }
}
