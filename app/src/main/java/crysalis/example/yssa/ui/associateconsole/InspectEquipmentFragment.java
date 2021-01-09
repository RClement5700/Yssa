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
        View.OnClickListener {

    RecyclerView rvInspectionList;
    InspectionSheetRecyclerViewAdapter adapter;
    Button completeBtn;
    CheckBox checkAll;

    public InspectEquipmentFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentInspectionBinding binding = FragmentInspectionBinding.inflate(inflater);
        View v = binding.getRoot();
        completeBtn = binding.completeBtn;
        rvInspectionList = binding.rvInspectionList;
        adapter = new InspectionSheetRecyclerViewAdapter(getContext());
        rvInspectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvInspectionList.setAdapter(adapter);
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
}
