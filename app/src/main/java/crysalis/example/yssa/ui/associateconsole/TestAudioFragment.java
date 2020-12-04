package crysalis.example.yssa.ui.associateconsole;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
        View v = inflater.inflate(R.layout.fragment_test_audio, container, false);
        FragmentTestAudioBinding binding = FragmentTestAudioBinding.bind(v);
        CircleLineVisualizer visualizer = binding.testAudioVizualizer;
        ImageButton imgBtnContinue = binding.imgBtnContinue;
        imgBtnContinue.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

    }
}
