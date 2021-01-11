package crysalis.example.yssa.ui.orderpicking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import crysalis.example.yssa.databinding.FragmentCurrentOrderBinding;
import pojos.Product;

public class CurrentOrderFragment extends Fragment {
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save current order details here
        //outState.addInt("curChoice", mCurCheckPosition);
    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // savedInstanceState will have whatever you left in the outState bundle above
        FragmentCurrentOrderBinding binding = FragmentCurrentOrderBinding.inflate(inflater);
        View v = binding.getRoot();
        return v;
    }

    public ArrayList<OrderItem> retrieveNextOrder() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(new Product(998, "Beef Patties"), 7));
        orderItems.add(new OrderItem(new Product(997, "Pork Ribs"), 9));
        orderItems.add(new OrderItem(new Product(996, "Pork Sausages"), 3));
        orderItems.add(new OrderItem(new Product(995, "Chicken Legs"), 2));
        orderItems.add(new OrderItem(new Product(994, "Chicken Breasts"), 3));
        orderItems.add(new OrderItem(new Product(993, "Beef Sausages"), 1));
        orderItems.add(new OrderItem(new Product(992, "Beef Steaks"), 5));
        orderItems.add(new OrderItem(new Product(991, "Pork Steaks"), 8));
        return orderItems;
    }
    
    public static class OrderItem {
        int pickQuantity;
        Product product;
        public OrderItem(Product product, int pickQuantity) {
            this.pickQuantity = pickQuantity;
            this.product = product;
        }

        public int getPickQuantity() {
            return pickQuantity;
        }

        public Product getProduct() {
            return product;
        }
    }
}