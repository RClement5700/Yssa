package crysalis.example.yssa.ui.associateconsole.chooserole;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import crysalis.example.yssa.databinding.FragmentChooseRoleBinding;


public class ChooseRoleFragment extends Fragment {
    Context mContext;
       /*TODO:
           -merge this Fragment and ChooseDepartmentFragment
           -change scroll direction to horizontal
           -when item is selected, it will light up and be added to a key:value pair
           -key:value pair will be passed to InspectEquipmentFragment
           -when item is deselected, it will be removed from the key:value pair
     */

    public ChooseRoleFragment(Context mContext) {
        //empty constructor
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChooseRoleBinding binding = FragmentChooseRoleBinding.inflate(inflater,
                container, false);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        View view = binding.getRoot();
        ViewPager viewPager = binding.rolesViewPager;
        viewPager.setAdapter(new ChooseRolePagerAdapter(fm, 0, mContext));
        TabLayout tabLayout = binding.rolesTabLayout;
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}