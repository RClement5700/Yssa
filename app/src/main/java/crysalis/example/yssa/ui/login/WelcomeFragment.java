package crysalis.example.yssa.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentWelcomeBinding;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;


public class WelcomeFragment extends Fragment {


    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentWelcomeBinding binding = FragmentWelcomeBinding.inflate(inflater, container,
                false);
        View v = binding.getRoot();
        ImageView ivProfilePicture = binding.ivProfilePicture;
        ImageButton imgBtnContinue = binding.imgBtnContinue;
        TextView tvFullName = binding.tvFullName;

        imgBtnContinue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //check if management or associate
                Intent intent = new Intent(getActivity(),
                        ManagementConsoleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        return v;
    }
}