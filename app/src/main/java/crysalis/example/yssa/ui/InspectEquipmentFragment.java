package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.databinding.FragmentInspectionBinding;

public class InspectEquipmentFragment extends Fragment {

    /*
    TODO:
        both homescreens will use this fragment but manipulate the recycler view to display different
            results
     */
    public InspectEquipmentFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentInspectionBinding binding =
                FragmentInspectionBinding.inflate(LayoutInflater.from(getContext()));
        View v = binding.getRoot();

        return v;
    }
}
