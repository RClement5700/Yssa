package crysalis.example.yssa.ui.associateconsole.chooserole;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import crysalis.example.yssa.R;

public class ChooseRolePagerAdapter extends FragmentPagerAdapter {

    private static String[] TAB_TITLES;
    private final Context mContext;
    FragmentManager fm;

    public ChooseRolePagerAdapter(@NonNull FragmentManager fm, int behavior, Context mContext) {
        super(fm, behavior);
        this.mContext = mContext;
        this.fm = fm;
        TAB_TITLES = mContext.getResources().getStringArray(R.array.role_titles);
    }

    @Override
    public Fragment getItem(int position) {
//        RoleItemViewFragment fragment = new RoleItemViewFragment(position);
//        return fragment;
        return new RoleItemViewFragment(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}