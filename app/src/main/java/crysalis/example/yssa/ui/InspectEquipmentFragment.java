package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.databinding.FragmentInspectionBinding;
import data.InspectionSheetRecyclerViewAdapter;

public class InspectEquipmentFragment extends Fragment {

    /*
    TODO:
        -add "Complete" Button
        -"Check All" checkbox should make the recyclerView scroll to the "Complete" button
        -clicking "Complete" btn will direct the user to either the forklift or pallet jack homescreen
        -if all boxes aren't checked, send email to supervisor
     */

    String[] checkListData;
    public InspectEquipmentFragment(String[] checkListData) {
        this.checkListData = checkListData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentInspectionBinding binding =
                FragmentInspectionBinding.inflate(LayoutInflater.from(getContext()));
        View v = binding.getRoot();
        Button completeBtn = binding.completeBtn;
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //direct to designated homescreen
            }
        });
        RecyclerView rvInspectionList = binding.rvInspectionList;
        rvInspectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvInspectionList.setAdapter(new InspectionSheetRecyclerViewAdapter(checkListData, getContext()));
        return v;
    }
}
