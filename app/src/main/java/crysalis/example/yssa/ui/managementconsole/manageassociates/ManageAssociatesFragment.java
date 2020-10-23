package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Employee;

public class ManageAssociatesFragment extends Fragment {

    private ManageAssociatesViewModel manageAssociatesViewModel;
    ManageAssociatesRecyclerViewAdapter adapter;
    Context context;
    ArrayList<Employee> employees;
    RecyclerView rvManageAssociates;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_manage_associates, container, false);
        context = getContext();
        final ImageButton addUserButton = root.findViewById(R.id.img_btn_add_user);
        final ImageButton deleteUserButton = root.findViewById(R.id.img_btn_delete_user);
        final ImageButton filterUserButton = root.findViewById(R.id.img_btn_filter_users);
        rvManageAssociates = root.findViewById(R.id.rv_manage_associates);
        rvManageAssociates.setAdapter(new ManageAssociatesRecyclerViewAdapter(employees));
        rvManageAssociates.setLayoutManager(new LinearLayoutManager(context));
        return root;
    }

    static class ConnectToSqlDatabaseTask extends AsyncTask<String, Void, Employee> {

        String url = "jdbc:mysql://10.0.2.2:8889/yssa";
        String admin_username = "root";
        String admin_password = "root";
        ArrayList<Employee> employees;
        Connection con;

        @Override
        protected Employee doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(url, admin_username, admin_password);
                System.err.println("Connecting to SQL database...");
                if (!con.isClosed()) {
                    System.err.println("SQL database connection complete");
                    retrieveUsers();

                }
            } catch (IllegalAccessException | java.lang.InstantiationException | SQLException |
                    ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void setEmployees(ArrayList<Employee> employees) {
            this.employees = employees;
        }

        private Employee retrieveUsers() {
            Employee employee = null;
            try {
                ResultSet rs = con.prepareStatement("SELECT * FROM `users`").executeQuery();
                while (rs.next()) {
                    int employeeId = rs.getInt(1);
                    String username = rs.getString(2);
                    String fullName = rs.getString(3);
                    employee = new Employee(employeeId, username, fullName);
                    employees.add(employee);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error loading products from MySQL...");
            }
            return employee;
        }
    }
}