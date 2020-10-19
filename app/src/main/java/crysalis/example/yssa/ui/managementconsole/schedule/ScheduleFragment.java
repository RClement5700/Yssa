package crysalis.example.yssa.ui.managementconsole.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import crysalis.example.yssa.R;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel scheduleViewModel;

    public ScheduleFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        scheduleViewModel =
                viewModelProvider.get(ScheduleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        final TextView textView = root.findViewById(R.id.text_schedule);
        scheduleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}