package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.R;

public class ForkliftHomescreenFragment extends Fragment {

    static String[] inspectionChecklist;
    public ForkliftHomescreenFragment() {
        //default constructor
        inspectionChecklist = getResources().getStringArray(R.array.forklift_inspection_data_list);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //cannot use databinding unless fragment/activity has a corresponding layout
        View v = inflater.inflate(R.layout.fragment_inspection, container, false);
        return v;
    }
}
