package crysalis.example.yssa.ui;

import android.content.Context;
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

        connectToSqlDatabase(getApplicationContext());
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


    //TODO: redownload and reconfigure mamp
    //      ask Garrett for sample URL
    public void connectToSqlDatabase(final Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://10.0.2.2:8889/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "MySQL oonnection successful: " + response,
                                Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //connect to backup cloud server
                        Toast.makeText(context, "Connection error: " + error,
                                Toast.LENGTH_LONG).show();
                        System.err.println("Error: " + error);
                    }
                }
        );

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}