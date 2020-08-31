package crysalis.example.yssa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {

    String username;
    String password;
    static final String TAG = "Choose Department Fragment";

    public RegisterFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentRegisterBinding binding = FragmentRegisterBinding.inflate(inflater);
        View v = binding.getRoot();
        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragments_container, new ChooseDepartmentFragment())
                        .addToBackStack(TAG)
                        .commit();
            }
        });
        EditText etUsername = binding.etRegisterUsername;
        EditText etPassword = binding.etRegisterPassword;
        return v;
    }
}
