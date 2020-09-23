package crysalis.example.yssa.ui.managementconsole;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import crysalis.example.yssa.databinding.FragmentAssignmentsBinding;

public class AssignmentsFragment extends Fragment {

    public AssignmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentAssignmentsBinding binding =
                FragmentAssignmentsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}