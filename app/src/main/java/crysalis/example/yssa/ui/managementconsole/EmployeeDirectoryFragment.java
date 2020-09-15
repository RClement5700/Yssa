package crysalis.example.yssa.ui.managementconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentEmployeeDirectoryBinding;
import pojos.Employee;


public class EmployeeDirectoryFragment extends Fragment {


    //TODO: All employee directory functions will take place here
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentEmployeeDirectoryBinding binding = 
                FragmentEmployeeDirectoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void addEmployeeToDirectory(Employee employee) {

    }

    public void deleteEmployeeFromDirectory(Employee employee) {

    }

    public void updateEmployeeInfo(Employee employee) {

    }
}