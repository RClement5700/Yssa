package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentChooseEquipmentBinding;

public class ChooseEquipmentFragment extends Fragment {

    String[] forkliftInspectionCheckList;
    String[] electricPalletJackInspectionChecklist;

    public ChooseEquipmentFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChooseEquipmentBinding binding = FragmentChooseEquipmentBinding.inflate(inflater,
                container, false);
        View view = binding.getRoot();
        forkliftInspectionCheckList
                = getResources().getStringArray(R.array.forklift_inspection_data_list);
        electricPalletJackInspectionChecklist
                = getResources().getStringArray(R.array.electric_pallet_jack_inspection_data_list);
        final FragmentManager fm = getActivity().getSupportFragmentManager();

        binding.forkliftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fm.beginTransaction()
                            .replace(R.id.fragments_container,
                                    new InspectEquipmentFragment(forkliftInspectionCheckList))
                            .commit();
                }

        });
        binding.electricPalletJackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.fragments_container,
                                new InspectEquipmentFragment(electricPalletJackInspectionChecklist ))
                        .commit();
            }
        });

        return view;
    }
}