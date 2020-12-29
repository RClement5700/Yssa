package crysalis.example.yssa.ui.associateconsole;

import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gauravk.audiovisualizer.visualizer.CircleLineVisualizer;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentTestMicrophoneBinding;


//https://github.com/gauravk95/audio-visualizer-android

public class TestMicrophoneFragment extends Fragment implements View.OnClickListener {

    ImageButton imgBtnMicrophone;
    ImageButton imgBtnContinue;
    AudioRecord recorder;
    public TestMicrophoneFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test_microphone, container, false);
        FragmentTestMicrophoneBinding binding = FragmentTestMicrophoneBinding.bind(v);
        CircleLineVisualizer visualizer = binding.testMicVizualizer;
        ProgressBar progressBar = binding.progressBar;
        recorder = new AudioRecord.Builder()
                .setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
                .build();
        imgBtnContinue = binding.imgBtnContinue;
        imgBtnMicrophone = binding.imgBtnMicrophone;
        imgBtnMicrophone.setOnClickListener(this);
        imgBtnContinue.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_microphone:
                recorder.startRecording();
                break;
            case R.id.img_btn_continue:

                break;
        }
    }
}
