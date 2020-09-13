package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
        final DrawerLayout managamentConsoleDrawer = binding.managementConsoleDrawer;
        managamentConsoleDrawer.measure();
        final NavigationView nvManagementConsole = binding.nvManagementConsole;
        nvManagementConsole.inflateMenu(R.menu.menu_management_console);
        nvManagementConsole.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                fm.beginTransaction()
                        .add(R.id.container_management_console, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
                managamentConsoleDrawer.closeDrawers();
                return menuItem.collapseActionView();
            }
        });
        View v = binding.getRoot();

        return v;
    }

    @Override
    public void onDirectoryUpdated() {

    }
}