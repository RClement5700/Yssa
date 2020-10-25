package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Employee;
import pojos.Order;

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