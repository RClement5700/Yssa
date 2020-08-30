package crysalis.example.yssa.ui;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentChooseDepartmentBinding;
import data.DepartmentRecyclerViewAdapter;

public class ChooseDepartmentFragment extends Fragment {

    /*TODO:
        -back button should direct user directly to MainActivity
        -Use tabs and viewpager instead of floating images
        -merge this Fragment and ChooseEquipmentFragment
    */
    public ChooseDepartmentFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentChooseDepartmentBinding binding = FragmentChooseDepartmentBinding.inflate(
                LayoutInflater.from(getContext()));
        View view = binding.getRoot();
        RecyclerView rvChooseDepartment = view.findViewById(R.id.rv_choose_department);
        DepartmentRecyclerViewAdapter departmentRecyclerViewAdapter =
                new DepartmentRecyclerViewAdapter(getActivity(),
                        getActivity().getSupportFragmentManager());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rvChooseDepartment.setAdapter(departmentRecyclerViewAdapter);
        rvChooseDepartment.setLayoutManager(llm);
        rvChooseDepartment.setHasFixedSize(true);
        return view;
    }
}
