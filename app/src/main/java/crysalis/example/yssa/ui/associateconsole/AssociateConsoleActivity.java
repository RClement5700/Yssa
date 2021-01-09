package crysalis.example.yssa.ui.associateconsole;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import crysalis.example.yssa.databinding.ActivityAssociateConsoleBinding;

public class AssociateConsoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAssociateConsoleBinding binding =
                ActivityAssociateConsoleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        TabLayout tabLayout = binding.associateTabLayout;
        ViewPager viewPager = binding.associateViewPager;
        viewPager.setAdapter(new AssociatesConsolePagerAdapter(getSupportFragmentManager(),
                0));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(android.R.drawable.ic_btn_speak_now);
        tabLayout.getTabAt(1).setIcon(android.R.drawable.ic_menu_edit);
        setContentView(view);
    }
}