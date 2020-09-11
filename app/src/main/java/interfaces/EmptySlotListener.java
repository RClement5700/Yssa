package interfaces;

import pojos.Product;
import pojos.Slot;

public interface EmptySlotListener {
    public void replenish(Slot slot, Product product);
}
