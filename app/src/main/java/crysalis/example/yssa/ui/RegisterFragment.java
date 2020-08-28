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

import crysalis.example.yssa.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment {

    String username;
    String password;

    public RegisterFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentRegisterBinding binding = FragmentRegisterBinding.inflate(inflater);
        View v = binding.getRoot();
        Button registerButton = binding.registerButton;
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });
        EditText etUsername = binding.etRegisterUsername;
        EditText etPassword = binding.etRegisterPassword;
        return v;
    }
}
