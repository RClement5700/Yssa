package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapters.OrderPickingRecyclerViewAdapter;
import adapters.SpacesItemDecoration;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentOrderPickingBinding;
import pojos.Department;
import pojos.Order;
import pojos.Product;

public class OrderPickingFragment extends Fragment {

    Department department;
    Order order;
    public OrderPickingFragment(Department department, Order order) {
        // Required empty public constructor
        this.department = department;
        this.order = order;
    }

    public OrderPickingFragment() {
        Product product = new Product(0, 0, 0,
                0, 0, null);
        int i = 0;
        ArrayList<Product> products = new ArrayList<Product>();
        while(i < 10) {
            products.add(product);
            i++;
        }
        order = new Order(products, 0, 0, 0, null,
                false, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentOrderPickingBinding binding =
                FragmentOrderPickingBinding.inflate(inflater, container, false);
        RecyclerView rvProductList = binding.rvProductList;
        rvProductList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProductList.setAdapter(new OrderPickingRecyclerViewAdapter(order, getContext()));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        rvProductList.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        View v = binding.getRoot();
        return v;
    }
}