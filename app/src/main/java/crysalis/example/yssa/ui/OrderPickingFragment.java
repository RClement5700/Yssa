package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentOrderPickingBinding;
import pojos.Department;
import pojos.Order;

public class OrderPickingFragment extends Fragment {

    public OrderPickingFragment(Department department, Order order) {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentOrderPickingBinding binding =
                FragmentOrderPickingBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        return v;
    }
}