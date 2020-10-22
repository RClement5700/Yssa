package crysalis.example.yssa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoginBinding;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;
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
                SharedPreferences preferences = getSharedPreferences("userCredentials", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("username", username);
                editor.putString("password", password);
                editor.apply();
                startActivity(new Intent(LoginActivity.this,
                        ManagementConsoleActivity.class));
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
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error loading products from MySQL...");
            }
            return employee;
        }
    }
}