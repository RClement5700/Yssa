package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import adapters.OrderPickingRecyclerViewAdapter;
import adapters.SpacesItemDecoration;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentOrderPickingBinding;
import pojos.Department;
import pojos.Order;

public class OrderPickingFragment extends Fragment {

    Department department;
    Order order;
    Connection connection;

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
//        downloadOrders("admin", "adminadmin",
//                "jdbc:mysql://10.0.2.2:8889/yssa");

        //cannot retrieve and display data from background process
        return v;
    }

//    public void downloadOrders(String username, String password, String url) {
//
//        Order order = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            connection = DriverManager.getConnection(url, username, password);
//            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM orders");
//            ResultSet resultSet = stmt.executeQuery();
//            if (resultSet.next()) {
//                order = new Order(null, resultSet.getInt(0), resultSet.getInt(1),
//                        0, null, false, null);
//            }
//
//        } catch (IllegalAccessException | java.lang.InstantiationException | SQLException |
//                ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}