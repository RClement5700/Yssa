package crysalis.example.yssa.ui.associateconsole;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    public TestMicrophoneFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test_microphone, container, false);
        FragmentTestMicrophoneBinding binding = FragmentTestMicrophoneBinding.bind(v);
        CircleLineVisualizer visualizer = binding.testMicrophoneVizualizer;
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
                ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                        imgBtnMicrophone,
                        PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                        PropertyValuesHolder.ofFloat("scaleY", 1.2f));
                scaleDown.setDuration(310);
                scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
                scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
                if (!isRecording) {
                    File file = new File(getActivity().getPackageName());
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//                    recorder.setOutputFile();
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                recorder.prepare();
                                recorder.start();
                                scaleDown.start();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else {
                        scaleDown.cancel();
                        recorder.stop();
                        recorder.release();
                    }
                break;

            case R.id.img_btn_play:

                break;
        }
    }
}
