package crysalis.example.yssa.ui.associateconsole.choosedepartment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ItemViewDepartmentBinding;
import crysalis.example.yssa.databinding.ItemViewRoleBinding;

public class DepartmentItemViewFragment extends Fragment {
    private static final int[] TAB_IMAGES = {R.drawable.grocery, R.drawable.produce,
            R.drawable.dairy, R.drawable.meat
    };
    CheckBox checkBox;
    ImageView imageView;
    int position;

    public DepartmentItemViewFragment(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ItemViewDepartmentBinding binding = ItemViewDepartmentBinding.inflate(inflater, container,
                false);
        View view = binding.getRoot();
        imageView = binding.ivDepartmentImage;
        imageView.setImageResource(TAB_IMAGES[position]);
        checkBox = binding.checkboxDepartment;
        return view;
    }
}
