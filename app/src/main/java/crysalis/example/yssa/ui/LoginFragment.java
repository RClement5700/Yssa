package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment {
    static final String TAG = "Choose Department Fragment";
    public LoginFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(inflater);
        View v = binding.getRoot();

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragments_container, new ChooseDepartmentFragment())
                        .addToBackStack(TAG)
                        .commit();
            }
        });
        return v;
    }
}
