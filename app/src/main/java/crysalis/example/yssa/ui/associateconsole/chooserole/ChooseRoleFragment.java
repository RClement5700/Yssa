package crysalis.example.yssa.ui.associateconsole.chooserole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import crysalis.example.yssa.databinding.FragmentChooseRoleBinding;


public class ChooseRoleFragment extends Fragment {

    public ChooseRoleFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChooseRoleBinding binding = FragmentChooseRoleBinding.inflate(inflater);
        View view = binding.getRoot();
        ViewPager viewPager = binding.rolesViewPager;
        viewPager.setAdapter(new ChooseRolePagerAdapter(getParentFragmentManager(),
                0, getContext()));
        TabLayout tabLayout = binding.rolesTabLayout;
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}