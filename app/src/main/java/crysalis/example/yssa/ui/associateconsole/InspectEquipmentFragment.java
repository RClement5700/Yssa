package crysalis.example.yssa.ui.associateconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import adapters.InspectionSheetRecyclerViewAdapter;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentInspectionBinding;

public class InspectEquipmentFragment extends Fragment {

    /*
    TODO:
        -add "Complete" Button
        -"Check All" checkbox should make the recyclerView scroll to the "Complete" button
        -clicking "Complete" btn will direct the user to either the forklift or pallet jack homescreen
        -if all boxes aren't checked, send email to supervisor
     */

    String[] checkListData;
    RecyclerView rvInspectionList;
    public InspectEquipmentFragment(String[] checkListData) {
        this.checkListData = checkListData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentInspectionBinding binding =
                FragmentInspectionBinding.inflate(LayoutInflater.from(getContext()));
        View v = binding.getRoot();
        Button completeBtn = binding.completeBtn;
        rvInspectionList = binding.rvInspectionList;
        final InspectionSheetRecyclerViewAdapter adapter =
                new InspectionSheetRecyclerViewAdapter(checkListData, getContext());
        rvInspectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvInspectionList.setAdapter(adapter);
        CheckBox checkAll = binding.checkboxCheckAll;
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                adapter.setAllCheckBoxes(b);
            }
        });
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //direct to designated homescreen
                fm.beginTransaction()
                        //need to pass data into OrderPickingFragment
//                        .replace(R.id.fragments_container, new AssignmentsListViewFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }
}
