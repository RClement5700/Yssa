package crysalis.example.yssa.ui.associateconsole;

import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import adapters.InspectionSheetRecyclerViewAdapter;
import crysalis.example.yssa.databinding.FragmentInspectionBinding;
import crysalis.example.yssa.ui.orderpicking.OrderPickingActivity;

public class InspectEquipmentFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    String[] checkListData;
    RecyclerView rvInspectionList;
    InspectionSheetRecyclerViewAdapter adapter;

    public InspectEquipmentFragment(String[] checkListData) {
        this.checkListData = checkListData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentInspectionBinding binding = FragmentInspectionBinding.inflate(inflater);
        View v = binding.getRoot();
        Button completeBtn = binding.completeBtn;
        rvInspectionList = binding.rvInspectionList;
        adapter = new InspectionSheetRecyclerViewAdapter(checkListData, getContext());
        rvInspectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvInspectionList.setAdapter(adapter);
        CheckBox checkAll = binding.checkboxCheckAll;
        checkAll.setOnCheckedChangeListener(this);
        completeBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        //TODO:
        Intent intent = new Intent(getActivity(), OrderPickingActivity.class);
        startActivity(intent);
    }
    @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            adapter.setAllCheckBoxes(b);
        }
}
