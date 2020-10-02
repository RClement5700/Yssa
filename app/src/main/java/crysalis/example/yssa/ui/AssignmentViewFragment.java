package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;

import adapters.AssignmentRecyclerViewAdapter;
import adapters.SpacesItemDecoration;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentAssignmentViewBinding;
import pojos.Assignment;
import pojos.Department;
import pojos.OrderPicker;
import services.YssaConnectionService;

public class AssignmentViewFragment extends Fragment {


    //order should have a list of products
    //assignment should have a list of slots relative to the products that have been ordered


    Department department;
    OrderPicker orderPicker;
    Assignment assignment;
    Connection sqlConnection;

    public AssignmentViewFragment(Department department, Assignment assignment) {
        // Required empty public constructor
        this.department = department;
        this.assignment = assignment;
    }

    public AssignmentViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAssignmentViewBinding binding =
                FragmentAssignmentViewBinding.inflate(inflater, container, false);
        RecyclerView rvProductList = binding.rvProductList;
        rvProductList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProductList.setAdapter(new AssignmentRecyclerViewAdapter(assignment, getContext()));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        rvProductList.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        View v = binding.getRoot();
        YssaConnectionService service = YssaConnectionService.getInstance();
        sqlConnection = service.getSqlConnection();
        return v;
    }
}