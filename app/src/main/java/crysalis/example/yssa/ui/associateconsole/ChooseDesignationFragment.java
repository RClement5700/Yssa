package crysalis.example.yssa.ui.associateconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import adapters.DesignationRecyclerViewAdapter;
import crysalis.example.yssa.databinding.FragmentChooseDesignationBinding;

public class ChooseDesignationFragment extends Fragment {

       /*TODO:
           -merge this Fragment and ChooseDepartmentFragment
           -change scroll direction to horizontal
           -when item is selected, it will light up and be added to a key:value pair
           -key:value pair will be passed to InspectEquipmentFragment
           -when item is deselected, it will be removed from the key:value pair
     */

    public ChooseDesignationFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChooseDesignationBinding binding = FragmentChooseDesignationBinding.inflate(inflater,
                container, false);
        View view = binding.getRoot();
        RecyclerView rvChooseDesignation = binding.rvChooseDesignation;
        rvChooseDesignation.setAdapter(new DesignationRecyclerViewAdapter(getContext(),
                getActivity().getSupportFragmentManager()));
        rvChooseDesignation.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


}