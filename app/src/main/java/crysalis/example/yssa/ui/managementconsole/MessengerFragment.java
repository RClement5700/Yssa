package crysalis.example.yssa.ui.managementconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.databinding.FragmentMessengerBinding;


public class MessengerFragment extends Fragment {

    //TODO: All messenger functions will take place here
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentMessengerBinding binding =
                FragmentMessengerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}