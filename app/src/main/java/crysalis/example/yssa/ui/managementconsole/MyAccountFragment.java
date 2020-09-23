package crysalis.example.yssa.ui.managementconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.databinding.FragmentMyAccountBinding;


public class MyAccountFragment extends Fragment {


    //TODO: Here, Management employees will be able to find and edit their user info
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentMyAccountBinding binding =
                FragmentMyAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}