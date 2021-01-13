package crysalis.example.yssa.ui.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tomer.fadingtextview.FadingTextView;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentVoiceProfileBinding;

public class VoiceProfileFragment extends Fragment implements View.OnClickListener {
    FadingTextView tvCreateVoiceProfile;
    ImageButton imgBtnMic;
    public VoiceProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentVoiceProfileBinding binding = FragmentVoiceProfileBinding.inflate(inflater);
        View v = binding.getRoot();
        tvCreateVoiceProfile = binding.tvCreateVoiceProfile;
        imgBtnMic = binding.imgBtnMicrophone;
        imgBtnMic.setOnClickListener(this);
        String[] prompts = {getString(R.string.prompt_create_voice_profile),
                getString(R.string.prompt_press_button)};
        tvCreateVoiceProfile.setTexts(prompts);
        tvCreateVoiceProfile.restart();
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
