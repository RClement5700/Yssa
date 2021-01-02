package crysalis.example.yssa.ui.associateconsole;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.VolumeProvider;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.gauravk.audiovisualizer.visualizer.CircleLineVisualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentTestMicrophoneBinding;


//https://github.com/gauravk95/audio-visualizer-android
// AudioRecord.Builder

public class TestMicrophoneFragment extends Fragment implements View.OnClickListener {

    ImageButton imgBtnMicrophone;
    ImageButton imgBtnPlay;
    ImageButton imgBtnStop;
    MediaRecorder recorder;
    MediaPlayer player;
    boolean isRecording;
    boolean isPlaying;
    String fileName = "recorded.3gp";
    String file = Environment.getExternalStorageDirectory().getAbsolutePath() + File.pathSeparator + fileName;
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
        imgBtnStop = binding.imgBtnStop;
        imgBtnStop.setOnClickListener(this);
        imgBtnMicrophone.setOnClickListener(this);
        imgBtnPlay.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_microphone:
                if (!isRecording && !isPlaying && checkMicPermissions()) {
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setOutputFile(file);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                recorder.prepare();
                                recorder.start();
                                isRecording = true;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;

            case R.id.img_btn_play:
                //TODO:
//                https://stackoverflow.com/questions/3761305/android-mediaplayer-throwing-prepare-failed-status-0x1-on-2-1-works-on-2-2
                if (!isPlaying && !isRecording && checkMicPermissions()) {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
//                                player = MediaPlayer.create(getActivity(), Uri.fromFile(new File(file)));
                                player = new MediaPlayer();
                                player.setScreenOnWhilePlaying(true);
                                player.setDataSource(file);
                                player.setVolume(5.0f, 5.0f);
                                player.setAuxEffectSendLevel(5.0f);
                                player.setAudioAttributes(new AudioAttributes.Builder()
                                        .setAllowedCapturePolicy(AudioAttributes.ALLOW_CAPTURE_BY_ALL)
                                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                                        .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
                                        .setUsage(AudioAttributes.USAGE_MEDIA)
                                        .setHapticChannelsMuted(true)
                                        .setLegacyStreamType(0)
                                .build());
                                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        mp.start();
                                        isPlaying = true;
                                    }
                                });
                                player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                                    @Override
                                    public boolean onError(MediaPlayer mp, int what, int extra) {
                                        mp.reset();
                                        System.err.println("Media Player failed to play");
                                        return isPlaying;
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;

            case R.id.img_btn_stop:
                if (isRecording) {
                    recorder.stop();
                    recorder.release();
                    isRecording = false;
                }
                if (isPlaying) {
                    player.stop();
                    player.release();
                    isPlaying = false;
                }
                break;
        }
    }

    private boolean checkMicPermissions(){
        int permissionWRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRECORD = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionWRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionRECORD != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.RECORD_AUDIO);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 0);
            return false;
        }
        return  true;
    }
}
