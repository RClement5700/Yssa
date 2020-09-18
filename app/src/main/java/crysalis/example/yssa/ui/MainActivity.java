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
import services.LoginService;

public class MainActivity extends AppCompatActivity {

    /* TODO:
    -openfire for messenger
    -openLdap for directory
    -mamp/mysql for external database
    -Room/SharedPreferences for internal storage of user credentials
    -all tasks and services will be run in this activity
    -need to write task in seperate intelliJ for bootstrapping order/load completion functions
    -need to write task in seperate intelliJ for bootstrapping retrieving workload from remote server
    -need to write task in seperate intelliJ for bootstrapping sending updates to remote server
    -Tomcat****
    -retrieve orders
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
//        new ConnectToSqlDatabase().execute();
        startService(new Intent(this, LoginService.class));
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
}