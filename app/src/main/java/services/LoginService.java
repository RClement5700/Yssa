package services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojos.Employee;

public class LoginService extends Service {

    public LoginService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new ConnectToSqlDatabaseTask().execute();
                new ConnectToLDAPDirectoryTask().execute();
            }
        };
        runnable.run();
        return super.onStartCommand(intent, flags, startId);
    }

    /*
    TODO:
        match username:employeeIDs in database to username:employeeIDs in directory
     */
    static class ConnectToSqlDatabaseTask extends AsyncTask<String, Void, String> {

        String url = "jdbc:mysql://10.0.2.2:8889/yssa";
        String username = "root";
        String password = "adminadmin";
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Connection con;

        @Override
        protected String doInBackground(String... strings) {

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM employees");
                ResultSet resultSet = stmt.executeQuery();
                if(resultSet.next()) {
                    System.err.println("Database Connected");
                    Employee employee =
                            new Employee(resultSet.getInt(2),
                                    resultSet.getString(1), resultSet.getString(3),
                                    resultSet.getString(4));
                    employees.add(employee);
                    System.err.println("username: " + employee.getUsername());
                }
            }
            catch (IllegalAccessException | InstantiationException | SQLException |
                    ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /*
  TODO:
      retrieve usernames from directory
   */
    static class ConnectToLDAPDirectoryTask extends AsyncTask<String, Void, String> {

        static ConnectToLDAPDirectoryTask task;
        int PORT = 389;
        LDAPConnection c;
        String address = "10.0.2.2";
        String dn = "cn=Manager,dc=catosystems,dc=com";
        String dnPassword = "uptown5700";

        public static ConnectToLDAPDirectoryTask getInstance() {
            if (task == null) {
                return new ConnectToLDAPDirectoryTask();
            }
            else {
                return task;
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                c = new LDAPConnection(address, PORT, dn, dnPassword); //,DN,password);
                System.err.println("Connecting to directory...");
                if (c.isConnected()) {
                    System.err.println("Directory Connection Complete");
//                    System.err.println(
//                            c.getEntry("uid="+uid+",ou=People,dc=catosystems,dc=com")
//                    );

                }
            } catch (LDAPException | RuntimeException e) {
                System.err.println("Error connecting to directory");
                e.printStackTrace();
            }
            return null;
        }
    }

    //openfire admin console http://172.20.4.51:9090
    static class ConnectToFireBaseTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            return null;
        }
    }

}
