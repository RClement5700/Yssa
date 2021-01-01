package crysalis.example.yssa.ui.associateconsole;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityAssociateConsoleBinding;

public class AssociateConsoleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAssociateConsoleBinding binding =
                ActivityAssociateConsoleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        TabLayout tabLayout = view.findViewById(R.id.associate_tab_layout);
        ViewPager viewPager = view.findViewById(R.id.associate_view_pager);
        viewPager.setAdapter(new AssociatesConsolePagerAdapter(getSupportFragmentManager(),
                0, this));
        tabLayout.setupWithViewPager(viewPager);
        setContentView(view);

    }

    @Override
    public void onClick(View view) {
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