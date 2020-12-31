package crysalis.example.yssa.ui.associateconsole;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityAssociateConsoleBinding;
import crysalis.example.yssa.ui.login.LoginActivity;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;

public class AssociateConsoleActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        ActivityAssociateConsoleBinding binding =
                ActivityAssociateConsoleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        FloatingActionButton fab = view.findViewById(R.id.fab_associate);
        fab.setOnClickListener(this);
        toolbar = view.findViewById(R.id.toolbar);
        TabLayout tabLayout = view.findViewById(R.id.associate_tab_layout);
        ViewPager viewPager = view.findViewById(R.id.associate_view_pager);
        viewPager.setAdapter(new AssociatesConsolePagerAdapter(getSupportFragmentManager(),
                0, this));
        tabLayout.setupWithViewPager(viewPager);
        setSupportActionBar(toolbar);
        setContentView(view);

    }

    @Override
    public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.associate_console_menu, menu);
//        menu.getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                mAuth.signOut();
//                startActivity(new Intent(AssociateConsoleActivity.this, LoginActivity.class));
//                return true;
//            }
//        });
//        menu.getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                //check if user is management user
//                startActivity(new Intent(AssociateConsoleActivity.this, ManagementConsoleActivity.class));
//                return true;
//            }
//        });
//        return true;
//    }
}