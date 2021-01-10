package crysalis.example.yssa.ui.associateconsole;

import android.content.Context;
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

import java.io.Serializable;
import java.util.ArrayList;

import adapters.InspectionSheetRecyclerViewAdapter;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentInspectionBinding;
import crysalis.example.yssa.ui.orderpicking.OrderPickingActivity;

public class InspectEquipmentFragment extends Fragment implements
        View.OnClickListener {

    RecyclerView rvInspectionList;
    CheckBox checkBox;
    InspectionSheetRecyclerViewAdapter adapter;
    Button completeBtn;
    String[] listOfParts;
    ArrayList<Obj> objList = new ArrayList<>();;
    Context context;

    public InspectEquipmentFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getContext();
        listOfParts = context.getResources().getStringArray(R.array.forklift_inspection_data_list);
        if (savedInstanceState == null) {
            generateChecklist();
        }
        else {
            objList = (ArrayList<Obj>) savedInstanceState.getSerializable("objList");
        }
        FragmentInspectionBinding binding = FragmentInspectionBinding.inflate(inflater);
        View v = binding.getRoot();
        checkBox = v.findViewById(R.id.checkbox_part);
        completeBtn = binding.completeBtn;
        rvInspectionList = binding.rvInspectionList;
        adapter = new InspectionSheetRecyclerViewAdapter(context, objList);
        rvInspectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvInspectionList.setAdapter(adapter);
        completeBtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("objList", objList);
    }

    public void generateChecklist() {
        for (String s: listOfParts) {
            objList.add(new Obj(false));
        }
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

    public static class Obj implements Serializable {
        boolean isSelected;
        public Obj(boolean isSelected) {
            this.isSelected = isSelected;
        }
        public boolean isSelected() {
            return isSelected;
        }
        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
