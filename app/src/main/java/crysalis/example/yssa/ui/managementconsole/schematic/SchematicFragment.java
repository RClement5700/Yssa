package crysalis.example.yssa.ui.managementconsole.schematic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import crysalis.example.yssa.R;

public class SchematicFragment extends Fragment {

    private SchematicViewModel schematicViewModel;

    public SchematicFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        schematicViewModel =
                viewModelProvider.get(SchematicViewModel.class);
        View root = inflater.inflate(R.layout.fragment_schematic, container, false);
        final TextView textView = root.findViewById(R.id.text_schematic);
        schematicViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}