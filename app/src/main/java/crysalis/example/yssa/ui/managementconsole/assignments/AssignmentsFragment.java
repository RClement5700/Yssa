package crysalis.example.yssa.ui.managementconsole.assignments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import crysalis.example.yssa.databinding.FragmentAssignmentsBinding;

public class AssignmentsFragment extends Fragment {

    public AssignmentsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
//        assignmentsViewModel =
//                viewModelProvider.get(AssignmentsViewModel.class);
//        final TextView textView = root.findViewById(R.id.text_assignments);
//        assignmentsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        RecyclerView rvOrders = root.findViewById(R.id.rv)
        FragmentAssignmentsBinding binding = FragmentAssignmentsBinding.inflate(inflater);
        View v = binding.getRoot();
        return v;
    }
}