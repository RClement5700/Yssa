package crysalis.example.yssa.ui.associateconsole.chooserole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.databinding.FragmentChooseRoleBinding;


public class ChooseRoleFragment extends Fragment {

       /*TODO:
           -merge this Fragment and ChooseDepartmentFragment
           -change scroll direction to horizontal
           -when item is selected, it will light up and be added to a key:value pair
           -key:value pair will be passed to InspectEquipmentFragment
           -when item is deselected, it will be removed from the key:value pair
     */

    public ChooseRoleFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChooseRoleBinding binding = FragmentChooseRoleBinding.inflate(inflater,
                container, false);
        View view = binding.getRoot();
        RecyclerView rvChooseRole = binding.rvChooseRole;
        RecyclerView rvChooseDepartment = binding.rvChooseDepartment;
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.HORIZONTAL);
        RoleRecyclerViewAdapter rva = new RoleRecyclerViewAdapter();
        rvChooseRole.setAdapter(rva);
        rvChooseRole.setLayoutManager(llm);
        return view;
    }

}