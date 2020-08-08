package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.R;

public class ElectricPalletJackHomescreenFragment extends Fragment {

    public ElectricPalletJackHomescreenFragment() {
        //default constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.fragment_inspection,
                 container, false);
         return v;
    }
}
