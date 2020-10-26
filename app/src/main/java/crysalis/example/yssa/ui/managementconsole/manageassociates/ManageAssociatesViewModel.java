package crysalis.example.yssa.ui.managementconsole.manageassociates;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import pojos.Employee;

public class ManageAssociatesViewModel extends ViewModel {

    MutableLiveData<ArrayList<Employee>> employeesLiveData;
    ArrayList<Employee> employeesArrayList;

    public ManageAssociatesViewModel() {
        employeesLiveData = new MutableLiveData<>();
    }


    public MutableLiveData<ArrayList<Employee>> getUserMutableLiveData() {
        return employeesLiveData;
    }
}