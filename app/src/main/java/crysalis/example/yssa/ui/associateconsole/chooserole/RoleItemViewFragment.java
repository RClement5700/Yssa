package crysalis.example.yssa.ui.associateconsole.chooserole;

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
import crysalis.example.yssa.databinding.ItemViewRoleBinding;

public class RoleItemViewFragment extends Fragment {
    private static final int[] TAB_IMAGES = {R.drawable.orderpicker, R.drawable.forklift,
            R.drawable.loader, R.drawable.training
    };
    CheckBox checkBox;
    ImageView imageView;
    int position;

    public RoleItemViewFragment(int position) {
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ItemViewRoleBinding binding = ItemViewRoleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        imageView = binding.ivRoleImage;
        imageView.setImageResource(TAB_IMAGES[position]);
        checkBox = binding.checkboxRole;
        return view;
    }
}
