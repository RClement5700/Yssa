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


//TODO: This class should be a fragment
public class ChooseEquipmentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentChooseEquipmentBinding binding = FragmentChooseEquipmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
            final FragmentManager fm = getActivity().getSupportFragmentManager();

            binding.forkliftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fm.beginTransaction()
                            .replace(R.id.fragments_container, new ForkliftHomescreenFragment())
                            .commit();
                }
            });
            binding.electricPalletJackBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fm.beginTransaction()
                            .replace(R.id.fragments_container, new ElectricPalletJackHomescreenFragment())
                            .commit();
                }
            });

        return view;
    }
}