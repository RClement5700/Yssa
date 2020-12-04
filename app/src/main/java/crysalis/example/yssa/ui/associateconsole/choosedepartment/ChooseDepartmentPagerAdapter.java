package crysalis.example.yssa.ui.associateconsole.choosedepartment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import crysalis.example.yssa.R;

public class ChooseDepartmentPagerAdapter extends FragmentPagerAdapter {

    private static String[] TAB_TITLES;
    FragmentManager fm;

    public ChooseDepartmentPagerAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.fm = fm;
        TAB_TITLES = context.getResources().getStringArray(R.array.department_titles);
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