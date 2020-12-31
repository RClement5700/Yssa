package crysalis.example.yssa.ui.associateconsole;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.gauravk.audiovisualizer.visualizer.CircleLineVisualizer;

import java.io.IOException;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentTestMicrophoneBinding;


//https://github.com/gauravk95/audio-visualizer-android
// AudioRecord.Builder

public class TestMicrophoneFragment extends Fragment implements View.OnClickListener {

    ImageButton imgBtnMicrophone;
    ImageButton imgBtnPlay;
    MediaRecorder recorder;
    MediaPlayer player;
    boolean isRecording;
    boolean isPlaying;
    int count;
    public TestMicrophoneFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test_microphone, container, false);
        FragmentTestMicrophoneBinding binding = FragmentTestMicrophoneBinding.bind(v);
        CircleLineVisualizer visualizer = binding.testMicrophoneVizualizer;
        count = 0;
        recorder = new MediaRecorder();
        player = new MediaPlayer();
        isRecording = false;
        isPlaying = false;
        imgBtnMicrophone = binding.imgBtnMicrophone;
        imgBtnPlay = binding.imgBtnPlay;
        imgBtnMicrophone.setOnClickListener(this);
        imgBtnPlay.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_microphone:
                if (!isRecording && !isPlaying) {
                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.RECORD_AUDIO}, getTargetRequestCode());

                        if (ActivityCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                            player.stop();
                            isPlaying = false;
                            recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
                            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                            recorder.setOutputFile("/");
                            try {
                                recorder.prepare();
                                recorder.start();
                                isRecording = true;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                else {
                    recorder.stop();
                    isRecording = false;
                }
                break;

            case R.id.img_btn_play:
                isPlaying = true;
                isRecording = false;
                try {
                    player.setDataSource("newMediaFile");
                    player.prepare();
                    player.start();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
