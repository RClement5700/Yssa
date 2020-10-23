package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Employee;
import pojos.Order;

public class ManageAssociatesViewModel extends ViewModel {

    MutableLiveData<ArrayList<Employee>> employeesLiveData;
    ArrayList<Employee> employeesArrayList;

    public ManageAssociatesViewModel() {
        employeesLiveData = new MutableLiveData<>();
        // call your Rest API in init method
        init();
    }


    public MutableLiveData<ArrayList<Employee>> getUserMutableLiveData() {
        return employeesLiveData;
    }

    public void init(){
        populateList();
        employeesLiveData.setValue(employeesArrayList);
    }

    private void populateList(){
        ConnectToSqlDatabaseTask task = new ConnectToSqlDatabaseTask();
        task.setEmployees(employeesArrayList);
        task.execute();
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