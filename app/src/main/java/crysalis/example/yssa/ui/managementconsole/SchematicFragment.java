package crysalis.example.yssa.ui.managementconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import crysalis.example.yssa.databinding.FragmentSchematicBinding;

public class SchematicFragment extends Fragment {

    public SchematicFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSchematicBinding binding =
                FragmentSchematicBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}