package crysalis.example.yssa.ui.associateconsole.choosedepartment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import crysalis.example.yssa.databinding.FragmentChooseDepartmentBinding;

public class ChooseDepartmentFragment extends Fragment {

    /*TODO:
        -back button should direct user directly to MainActivity
        -Use tabs and viewpager instead of floating images
        -merge this Fragment and ChooseEquipmentFragment
    */

    Context mContext;
    FragmentManager fm;
    public ChooseDepartmentFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentChooseDepartmentBinding binding = FragmentChooseDepartmentBinding.inflate(
                LayoutInflater.from(getContext()));
        mContext = getContext();
        fm = getActivity().getSupportFragmentManager();
        ViewPager viewPager = binding.departmentViewPager;
        viewPager.setAdapter(new ChooseDepartmentPagerAdapter(fm, 0, mContext));
        TabLayout tabLayout = binding.departmentTabLayout;
        tabLayout.setupWithViewPager(viewPager);
        View view = binding.getRoot();
        return view;
    }



}
