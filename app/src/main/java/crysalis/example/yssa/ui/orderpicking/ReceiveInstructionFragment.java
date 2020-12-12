package crysalis.example.yssa.ui.orderpicking;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.databinding.FragmentReceiveInstructionBinding;

public class ReceiveInstructionFragment extends Fragment implements View.OnClickListener {

    ObjectAnimator pulseAnimation;
    ImageButton imgBtnListening;
    public ReceiveInstructionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentReceiveInstructionBinding binding = FragmentReceiveInstructionBinding.inflate(inflater);
        View v = binding.getRoot();
        imgBtnListening = binding.imgBtnListening;
        imgBtnListening.setOnClickListener(this);
        pulseAnimation = ObjectAnimator.ofPropertyValuesHolder(
                imgBtnListening,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        pulseAnimation.setDuration(310);
        pulseAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        pulseAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (pulseAnimation.isStarted()) {
            pulseAnimation.cancel();
        }
        else {
            pulseAnimation.start();
        }
    }
}
