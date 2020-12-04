package crysalis.example.yssa.ui.associateconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.util.ArrayList;

import adapters.AssignmentRecyclerViewAdapter;
import adapters.SpacesItemDecoration;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentAssignmentListViewBinding;
import pojos.Assignment;
import pojos.Department;

public class AssignmentsListViewFragment extends Fragment {

    Department department;
    ArrayList<Assignment> assignments;
    Connection sqlConnection;

    public AssignmentsListViewFragment(Department department,
                                       ArrayList<Assignment> assignments) {
        // Required empty public constructor
        this.department = department;
        this.assignments = assignments;
    }

    public AssignmentsListViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAssignmentListViewBinding binding =
                FragmentAssignmentListViewBinding.inflate(inflater, container, false);
        RecyclerView rvProductList = binding.rvProductList;
        rvProductList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProductList.setAdapter(new AssignmentRecyclerViewAdapter(assignments, getContext()));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        rvProductList.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        View v = binding.getRoot();
        return v;
    }
}