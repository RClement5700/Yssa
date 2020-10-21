package crysalis.example.yssa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoginBinding;
import pojos.Employee;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    ProgressBar loading;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.GONE);
        loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.err.println("attempting login");
                loading.setVisibility(View.VISIBLE);
                int username = Integer.parseInt(etUsername.getText().toString());
                String password = etPassword.getText().toString();
                ConnectToSqlDatabaseTask loginTask = new ConnectToSqlDatabaseTask();
                loginTask.setUserId(username);
                loginTask.setPassword(password);
                loginTask.execute();
            }
        });
    }

    static class ConnectToSqlDatabaseTask extends AsyncTask<String, Void, Employee> {

        String url = "jdbc:mysql://10.0.2.2:8889/yssa";
        String admin_username = "root";
        String admin_password = "root";
        int userId;
        String password;
        Connection con;

        @Override
        protected Employee doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(url, admin_username, admin_password);
                System.err.println("Connecting to SQL database...");
                if (!con.isClosed()) {
                    System.err.println("SQL database connection complete");
                    return login(userId, password);
                }
            } catch (IllegalAccessException | InstantiationException | SQLException |
                    ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private Employee login(int userId, String password) {
            Employee employee = null;
            try {
                ResultSet rs = con.prepareStatement("SELECT * FROM `users` WHERE `UserId`="
                        + userId + " and `password`=\"" + password + "\"").executeQuery();
                while (rs.next()) {
                    int employeeId = rs.getInt(1);
                    String username = rs.getString(2);
                    String fullName = rs.getString(3);
                    employee = new Employee(employeeId, username, fullName);
//                    employees.add(employee);
                    System.err.println("users: " + username);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error loading products from MySQL...");
            }
            return employee;
        }
    }
}