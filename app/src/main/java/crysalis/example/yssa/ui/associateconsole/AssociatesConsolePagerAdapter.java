package crysalis.example.yssa.ui.associateconsole;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import crysalis.example.yssa.R;

public class AssociatesConsolePagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.test_audio, R.string.test_microphone,
    R.string.inspectequipment};
    private Context mContext;
    FragmentManager fm;

    public AssociatesConsolePagerAdapter(@NonNull FragmentManager fm, int behavior, Context mContext) {
        super(fm, behavior);
        this.mContext = mContext;
        this.fm = fm;
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        if (position == 2) {
            return new InspectEquipmentFragment();
        }
        else if (position == 1) {
            return new TestMicrophoneFragment();
        }
        return new TestAudioFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}