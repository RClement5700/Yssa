package crysalis.example.yssa.ui.associateconsole;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.gauravk.audiovisualizer.visualizer.CircleLineVisualizer;
import com.google.android.exoplayer2.C;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentTestMicrophoneBinding;

public class TestMicrophoneFragment extends Fragment implements View.OnClickListener {

    ImageButton imgBtnMicrophone;
    ImageButton imgBtnPlay;
    ImageButton imgBtnStop;
    MediaRecorder recorder;
    MediaPlayer player;
    boolean isRecording;
    boolean isPlaying;
    int REQUEST_CODE = 1000;
    String fileName = "recorded.3gp";
    String pathSave = null;

    public TestMicrophoneFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test_microphone, container, false);
        if (!checkMicPermissions()) requestPermissions();
        FragmentTestMicrophoneBinding binding = FragmentTestMicrophoneBinding.bind(v);
        CircleLineVisualizer visualizer = binding.testMicrophoneVizualizer;
        isRecording = false;
        isPlaying = false;
        imgBtnMicrophone = binding.imgBtnMicrophone;
        imgBtnPlay = binding.imgBtnPlay;
        imgBtnStop = binding.imgBtnStop;
        imgBtnMicrophone.setOnClickListener(this);
        imgBtnStop.setOnClickListener(this);
        imgBtnPlay.setOnClickListener(this);
        return binding.getRoot();
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO
        },REQUEST_CODE);
    }

    private boolean checkMicPermissions(){
        int permissionWRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRECORD = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO);
        return permissionWRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED &&
                permissionRECORD == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Permissions Granted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "Permissions Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_microphone:
                if (checkMicPermissions()) {
                    pathSave = getContext().getExternalFilesDir(null).getAbsolutePath() +
                            UUID.randomUUID().toString() + fileName;
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setOutputFile(pathSave);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    try {
                        recorder.prepare();
                        recorder.start();
                        isRecording = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                requestPermissions();
                }
                break;

            case R.id.img_btn_play:
                //TODO:
//                https://stackoverflow.com/questions/3761305/android-mediaplayer-throwing-prepare-failed-status-0x1-on-2-1-works-on-2-2
                if (checkMicPermissions()) {
                    player = new MediaPlayer();
                        try {
                            File file = new File(pathSave);
                            file.setReadable(true, false);
                            FileInputStream fis = new FileInputStream(file);
                            player.setDataSource(fis.getFD());
                            player.setAudioAttributes(new AudioAttributes.Builder()
                                    .setAllowedCapturePolicy(AudioAttributes.ALLOW_CAPTURE_BY_ALL)
                                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                                    .setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                            );
                            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    mp.reset();
                                    mp.release();
                                }
                            });
                            System.err.println("Sound File Path: " +pathSave);
                            player.prepare();
                            player.start();
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                }
                break;

            case R.id.img_btn_stop:
                if (isRecording) {
                    recorder.stop();
                    recorder.reset();
                    recorder.release();
                    isRecording = false;
                    try {
                        System.err.println("File Size: " + Files.size(Paths.get(pathSave)));
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                if (isPlaying) {
                    player.stop();
                    player.reset();
                    player.release();
                    isPlaying = false;
                }
                break;
        }
    }
}
