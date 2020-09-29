package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import adapters.OrderPickingRecyclerViewAdapter;
import adapters.SpacesItemDecoration;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentOrderPickingBinding;
import pojos.Assignment;
import pojos.Department;
import pojos.Order;
import pojos.OrderPicker;
import pojos.Product;
import services.YssaConnectionService;

public class OrderPickingFragment extends Fragment {


    //order should have a list of products
    //assignment should have a list of slots relative to the products that have been ordered


    Department department;
    OrderPicker orderPicker;
    Order order;
    Assignment assignment;
    Connection sqlConnection;

    public OrderPickingFragment(Department department, Order order) {
        // Required empty public constructor
        this.department = department;
        this.order = order;
    }

    public OrderPickingFragment() {
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
        YssaConnectionService service = YssaConnectionService.getInstance();
        sqlConnection = service.getSqlConnection();
        return v;
    }

    public Order downloadOrder() {

        Order order = null;
        try {
            PreparedStatement stmt = sqlConnection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {

                //USE resultSet TO FILL IN THE PARAMETERS

                ArrayList<Product> products;
                int orderNumber; //get from SQL
                int customerNumber; //get from SQL
                int sectionNumber; //department
                int palletCount; //determined by cubage
                Time startTime = (Time) Calendar.getInstance().getTime();
                Time completeTime = null; //get time from Calendar when status changes
                String location = null; //will be designated by Management
                Order.STATUS status = Order.STATUS.ASSIGNED;

                //order = new Order();
            }
        } catch (SQLException e) {
            Toast.makeText(getContext(),
                    "Failed to download orders", Toast.LENGTH_LONG).show();
            System.err.println("Failed to download orders");
            e.printStackTrace();
        }
        return order;
    }
}