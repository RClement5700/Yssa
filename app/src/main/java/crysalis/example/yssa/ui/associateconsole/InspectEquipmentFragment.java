package crysalis.example.yssa.ui.associateconsole;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import adapters.InspectionSheetRecyclerViewAdapter;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentInspectionBinding;
import crysalis.example.yssa.ui.orderpicking.OrderPickingActivity;

public class InspectEquipmentFragment extends Fragment implements
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    RecyclerView rvInspectionList;
    InspectionSheetRecyclerViewAdapter adapter;

    public InspectEquipmentFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        String[] checkListData =
                getActivity().getResources().getStringArray(R.array.forklift_inspection_data_list);
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
        if (adapter.allBoxesChecked()) {
            Intent intent = new Intent(getActivity(), OrderPickingActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getContext(), "Please notify supervisor if equipment is unsafe",
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            adapter.setAllCheckBoxes(b);
        }
}
