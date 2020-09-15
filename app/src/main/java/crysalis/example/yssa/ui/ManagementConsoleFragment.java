package crysalis.example.yssa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentManagementConsoleBinding;
import interfaces.DirectoryUpdatedListener;

public class ManagementConsoleFragment extends Fragment implements DirectoryUpdatedListener {

    //TODO: register fragment will be part of the navigationview
    public ManagementConsoleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentManager fm = getActivity().getSupportFragmentManager();

        FragmentManagementConsoleBinding binding =
                FragmentManagementConsoleBinding.inflate(inflater, container, false);
        Button loginBtn = binding.managementLoginBtn;
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ManagementConsoleActivity.class));
            }
        });
        View v = binding.getRoot();
        return v;
    }

    @Override
    public void onDirectoryUpdated() {

    }
}