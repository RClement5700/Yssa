package crysalis.example.yssa.ui.associateconsole;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityAssociateConsoleBinding;
import crysalis.example.yssa.ui.LoginActivity;
import crysalis.example.yssa.ui.associateconsole.chooserole.ChooseRoleFragment;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;

public class AssociateConsoleActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_associate_console);
        ActivityAssociateConsoleBinding binding = ActivityAssociateConsoleBinding.inflate(getLayoutInflater());
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.associate_console_container, new ChooseRoleFragment())
                .addToBackStack(null)
                .commit();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.associate_console_menu, menu);
        menu.getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mAuth.signOut();
                startActivity(new Intent(AssociateConsoleActivity.this, LoginActivity.class));
                return true;
            }
        });
        menu.getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //check if user is management user
                startActivity(new Intent(AssociateConsoleActivity.this, ManagementConsoleActivity.class));
                return true;
            }
        });
        return true;
    }
}