package crysalis.example.yssa.ui.managementconsole;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

public class ManagementConsoleViewModel extends ViewModel {

    public ManagementConsoleViewModel(Context context) {
    }

    public void inflateFragment(Fragment fragment, FragmentManager fm, int containerId) {
        if (fm.getBackStackEntryCount() == 0) {
            fm.beginTransaction()
                    .add(containerId, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            fm.beginTransaction()
                    .replace(containerId, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

}
