package crysalis.example.yssa.ui.managementconsole.assignments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Employee;
import pojos.Order;

public class AssignmentsFragment extends Fragment {

    private AssignmentsViewModel assignmentsViewModel;
    private ArrayList<Order> orders;

    public AssignmentsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
//        assignmentsViewModel =
//                viewModelProvider.get(AssignmentsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_assignments, container, false);
//        final TextView textView = root.findViewById(R.id.text_assignments);
//        assignmentsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        orders = new ArrayList<>();
        ConnectToSqlDatabaseTask loadOrdersTask = new ConnectToSqlDatabaseTask();
        loadOrdersTask.setOrders(orders);
        loadOrdersTask.execute();
//        RecyclerView rvOrders = root.findViewById(R.id.rv)
        return root;
    }

    static class ConnectToSqlDatabaseTask extends AsyncTask<String, Void, Employee> {

        String url = "jdbc:mysql://10.0.2.2:8889/yssa";
        String admin_username = "root";
        String admin_password = "root";
        Connection con;
        ArrayList<Order> orders;


        @Override
        protected Employee doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(url, admin_username, admin_password);
                System.err.println("Connecting to SQL database...");
                if (!con.isClosed()) {
                    System.err.println("SQL database connection complete");
                    retrieveOrders();
                }
            } catch (IllegalAccessException | java.lang.InstantiationException | SQLException |
                    ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void setOrders(ArrayList<Order> orders) {
            this.orders = orders;
        }

        private void retrieveOrders() {
            try {
                ResultSet rs = con.prepareStatement("SELECT * FROM `orders`").executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt(1);
                    boolean isAssigned = rs.getBoolean(2);
                    int userId = rs.getInt(3);
                    Order order = new Order(orderId, userId, isAssigned);
                    orders.add(order);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error loading products from MySQL...");
            }
        }
    }
}