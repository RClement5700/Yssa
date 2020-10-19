package crysalis.example.yssa.ui.managementconsole.myaccount;

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

import crysalis.example.yssa.R;

public class MyAccountFragment extends Fragment {

    private MyAccountViewModel myAccountViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        myAccountViewModel =
                viewModelProvider.get(MyAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_account, container, false);
        final TextView textView = root.findViewById(R.id.welcome_management_employee);
        myAccountViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}