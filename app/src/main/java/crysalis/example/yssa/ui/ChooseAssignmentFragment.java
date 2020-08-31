package crysalis.example.yssa.ui;

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

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentChooseAssignmentBinding;
import data.AssignmentRecyclerViewAdapter;

public class ChooseAssignmentFragment extends Fragment {

       /*TODO:
           -merge this Fragment and ChooseDepartmentFragment
           -change scroll direction to horizontal
           -when item is selected, it will light up and be added to a key:value pair
           -key:value pair will be passed to InspectEquipmentFragment
           -when item is deselected, it will be removed from the key:value pair
     */

    public ChooseAssignmentFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChooseAssignmentBinding binding = FragmentChooseAssignmentBinding.inflate(inflater,
                container, false);
        View view = binding.getRoot();
        RecyclerView rvChooseAssignment = binding.rvChooseAssigment;
        rvChooseAssignment.setAdapter(new AssignmentRecyclerViewAdapter(getContext(),
                getActivity().getSupportFragmentManager()));
        rvChooseAssignment.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


}