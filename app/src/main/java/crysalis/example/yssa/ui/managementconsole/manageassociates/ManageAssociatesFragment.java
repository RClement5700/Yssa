package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;

public class ManageAssociatesFragment extends Fragment {

    private ManageAssociatesViewModel manageAssociatesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        manageAssociatesViewModel =
                viewModelProvider.get(ManageAssociatesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_manage_associates, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
        final RecyclerView rvManageAssociates = (RecyclerView) root.findViewById(R.id.rv_manage_associates);
//        manageAssociatesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}