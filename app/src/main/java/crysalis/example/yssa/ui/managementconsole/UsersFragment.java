package crysalis.example.yssa.ui.managementconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.databinding.FragmentUsersBinding;
import pojos.Employee;


public class UsersFragment extends Fragment {


    //TODO: All employee directory functions will take place here
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentUsersBinding binding =
                FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void addEmployeeToDirectory(Employee employee) {

    }

    public void deleteEmployeeFromDirectory(Employee employee) {

    }

    public void updateEmployeeInfo(Employee employee) {

    }
}