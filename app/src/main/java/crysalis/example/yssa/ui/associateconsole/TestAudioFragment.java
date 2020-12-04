package crysalis.example.yssa.ui.associateconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.gauravk.audiovisualizer.visualizer.CircleLineVisualizer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentTestAudioBinding;


//https://github.com/gauravk95/audio-visualizer-android

public class TestAudioFragment extends Fragment implements View.OnClickListener {

    public TestAudioFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentTestAudioBinding binding = FragmentTestAudioBinding.inflate(inflater);
        View v = binding.getRoot();
        CircleLineVisualizer visualizer = binding.testAudioVizualizer;
        ProgressBar progressBar = binding.progressBar;
        ImageButton imgBtnContinue = binding.imgBtnContinue;
        imgBtnContinue.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
