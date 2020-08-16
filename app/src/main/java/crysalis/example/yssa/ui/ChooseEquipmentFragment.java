package crysalis.example.yssa.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentChooseEquipmentBinding;

public class ChooseEquipmentFragment extends Fragment {

    static String[] forkliftInspectionCheckList;
    static String[] electricPalletJackInspectionChecklist;

    public ChooseEquipmentFragment() {
        forkliftInspectionCheckList
                = getResources().getStringArray(R.array.forklift_inspection_data_list);
        electricPalletJackInspectionChecklist
                = getResources().getStringArray(R.array.electric_pallet_jack_inspection_data_list);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentChooseEquipmentBinding binding = FragmentChooseEquipmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        final FragmentManager fm = getActivity().getSupportFragmentManager();

        /*
        TODO:
            -Both forkliftBtn and electricpalletjackBtn will take user to InspectEquipmentFragment
         */

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