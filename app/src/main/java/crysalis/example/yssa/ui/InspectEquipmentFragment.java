package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentInspectionBinding;
import data.InspectionSheetRecyclerViewAdapter;

public class InspectEquipmentFragment extends Fragment {

    /*
    TODO:
        -both homescreens will use this fragment but manipulate the recycler view to display different
            results
        -After completing the checklist, user will be directed to either the ForkliftHomeScreenFragment
                or the ElectricPalletJackHomeScreen
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
        RecyclerView rvInspectionList = binding.rvInspectionList;
        rvInspectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvInspectionList.setAdapter(new InspectionSheetRecyclerViewAdapter(checkListData, getContext()));
        return v;
    }
}
