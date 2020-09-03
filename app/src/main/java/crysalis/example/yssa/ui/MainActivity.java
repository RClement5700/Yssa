package crysalis.example.yssa.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crysalis.example.yssa.R;
import data.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "Login Fragment";

    /* TODO:
    -openfire for userdata base
    -Room for internal storage of user credentials etc
    -all tasks and services will be run in this activity
    -Don't replace fragments when buttons are pushed; addToBackStack instead and update back button
        accordingly
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

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();

        }
    }

    //openfire admin console http://172.20.4.51:9090


    static class ConnectToSqlDatabase extends AsyncTask<String, Void, String> {

        String url = "jdbc:mysql://10.0.2.2:8889/yssa";
        String username = "root";
        String password = "adminadmin";

        @Override
        protected String doInBackground(String... strings) {

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM employees");
                ResultSet resultSet = stmt.executeQuery();
                if(resultSet.next()) {
                    System.err.println(resultSet.getString(1));
                };
            }
            catch (IllegalAccessException | InstantiationException | SQLException |
                    ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}