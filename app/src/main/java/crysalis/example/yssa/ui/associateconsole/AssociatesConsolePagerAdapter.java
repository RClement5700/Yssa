package crysalis.example.yssa.ui.associateconsole;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AssociatesConsolePagerAdapter extends FragmentPagerAdapter {

    private static final Fragment[] fragments = {new TestMicrophoneFragment(),
            new InspectEquipmentFragment()};
    FragmentManager fm;

    public AssociatesConsolePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.fm = fm;
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}