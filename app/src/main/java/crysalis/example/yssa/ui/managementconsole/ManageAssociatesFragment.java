package crysalis.example.yssa.ui.managementconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.databinding.FragmentManageAssociatesBinding;
import pojos.Employee;


public class ManageAssociatesFragment extends Fragment {


    //TODO: All employee directory functions will take place here
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentManageAssociatesBinding binding =
                FragmentManageAssociatesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void addEmployeeToDirectory(Employee employee) {

    }

    public void deleteEmployeeFromDirectory(Employee employee) {

    }

    public void updateEmployeeInfo(Employee employee) {

    }
}