package crysalis.example.yssa.ui.managementconsole;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import crysalis.example.yssa.R;
import crysalis.example.yssa.ui.login.LoginActivity;
import crysalis.example.yssa.ui.associateconsole.AssociateConsoleActivity;
import pojos.Employee;

public class ManagementConsoleActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    static Employee currentEmployee;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    static final String TAG = "ManagementConsoleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_console);
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_management_console);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        TextView navHeaderUsername
                = navigationView.getHeaderView(0).findViewById(R.id.nav_header_username);
        TextView navHeaderEmail
                = navigationView.getHeaderView(0).findViewById(R.id.nav_header_email);
//        if (task.getStatus() == AsyncTask.Status.FINISHED) {
//            navHeaderEmail.setText(mAuth.getCurrentUser().getEmail());
//            navHeaderUsername.setText(currentEmployee.getFullName());
//        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.my_account,
                R.id.manage_associates,
                R.id.messenger,
                R.id.schematic,
                R.id.assignments,
                R.id.schedule)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.management_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void initFirestore() {
        mFirestore = FirebaseFirestore.getInstance();
        // Get the 50 highest rated restaurants
        Query mQuery = mFirestore.collection("users")
                .orderBy("email", Query.Direction.DESCENDING)
                .limit(10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.management_console_menu, menu);
        menu.getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mAuth.signOut();
                startActivity(new Intent(ManagementConsoleActivity.this, LoginActivity.class));
                return true;
            }
        });
        menu.getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(ManagementConsoleActivity.this, AssociateConsoleActivity.class));
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.management_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}