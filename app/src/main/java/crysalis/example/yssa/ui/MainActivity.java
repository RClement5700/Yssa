package crysalis.example.yssa.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import crysalis.example.yssa.R;
import adapters.SectionsPagerAdapter;
import pojos.Employee;

public class MainActivity extends AppCompatActivity {
    /* TODO:
    -openfire for messenger
    -openLdap for directory
    -mamp/mysql for external database
    -Room/SharedPreferences for internal storage of user credentials
    -all tasks and services will be run in this activity
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter =
                new SectionsPagerAdapter(getSupportFragmentManager(), 1, this);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        //use if Managers want to send employees messages and vice versa
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        new ConnectToSqlDatabase().execute();
        new ConnectToLDAPDirectory(getApplicationContext()).execute();

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        }
        else if(getSupportFragmentManager().getFragments().get(0) ==
                getSupportFragmentManager().findFragmentByTag("android:switcher:2131362085:0") &&
            getSupportFragmentManager().getBackStackEntryCount() == 1)
        {
            startActivity(new Intent(this, MainActivity.class));
        }
        else {
            getSupportFragmentManager().popBackStack();
        }
    }

    /*
    TODO:
        match username:employeeIDs in database to username:employeeIDs in directory
     */
    static class ConnectToSqlDatabase extends AsyncTask<String, Void, String> {

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

        //will be moved to ConnectToLDAPDirectory
        public ArrayList<Employee> getEmployees() {
            return employees;
        }
    }


    /*
    TODO:
        retrieve usernames from directory
     */
    static class ConnectToLDAPDirectory extends AsyncTask<String, Void, String> {

        Context context;
        ConnectToLDAPDirectory(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {

            int PORT = 389;
            LDAPConnection c;
            String address = "10.0.2.2";
            try {
                c = new LDAPConnection(address, PORT); //,DN,password);
               System.err.println("Connecting to directory...");
                if (c.isConnected()) {
                    System.err.println("Connection complete...");
                    c.bind("cn=Manager,dc=catosystems,dc=com","uptown5700");
//                    c.searchForEntry(new SearchRequest());
                }
            } catch (LDAPException | RuntimeException e) {
                System.err.println("Error connecting to directory");
                e.printStackTrace();
            }
            return null;
        }

//        public ArrayList<Employee> getEmployees() {
//            return employees;
//        }
    }

    //openfire admin console http://172.20.4.51:9090
    static class ConnectToXMPPServer extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            return null;
        }
    }

}