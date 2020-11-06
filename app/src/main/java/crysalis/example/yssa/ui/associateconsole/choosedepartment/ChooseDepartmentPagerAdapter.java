package crysalis.example.yssa.ui.associateconsole.choosedepartment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import crysalis.example.yssa.R;
import crysalis.example.yssa.ui.associateconsole.chooserole.RoleItemViewFragment;

public class ChooseDepartmentPagerAdapter extends FragmentPagerAdapter {

    private static String[] TAB_TITLES;
    private final Context mContext;
    FragmentManager fm;

    public ChooseDepartmentPagerAdapter(@NonNull FragmentManager fm, int behavior, Context mContext) {
        super(fm, behavior);
        this.mContext = mContext;
        this.fm = fm;
        TAB_TITLES = mContext.getResources().getStringArray(R.array.department_titles);
    }

    @Override
    public Fragment getItem(int position) {
        return new DepartmentItemViewFragment(position);
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