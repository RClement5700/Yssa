package crysalis.example.yssa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unboundid.ldap.sdk.LDAPConnection;

import java.sql.Connection;

import adapters.OrderPickingRecyclerViewAdapter;
import adapters.SpacesItemDecoration;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentOrderPickingBinding;
import pojos.Department;
import pojos.Order;
import services.LoginService;

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
        LoginService service = LoginService.getInstance();
        Connection sqlConnection = service.getSqlConnection();
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