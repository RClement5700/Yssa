package crysalis.example.yssa.ui.main;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import com.tomer.fadingtextview.FadingTextView;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecognitionListener {

    private SpeechRecognizer speechRecognizer;
    private Intent recognizerIntent;
    ImageButton imgBtnContinue;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        String[] welcome = {getString(R.string.welcome_to_levo_sonus), getString(R.string.infer_sign_in)};
        FadingTextView tvWelcome = binding.tvWelcome;
        tvWelcome.setTexts(welcome);
        tvWelcome.restart();
        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_PLAY_SOUND);
        imgBtnContinue = binding.imgBtnContinue;
        imgBtnContinue.setOnClickListener(this);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);
        recognizerIntent = new Intent();
        speechRecognizer.startListening(recognizerIntent);
        setContentView(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        speechRecognizer.startListening(recognizerIntent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        speechRecognizer.startListening(recognizerIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        speechRecognizer.startListening(recognizerIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        speechRecognizer.stopListening();
        speechRecognizer.destroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        speechRecognizer.stopListening();
        speechRecognizer.destroy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.stopListening();
        speechRecognizer.destroy();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, LoginRegisterActivity.class));
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        System.err.println("On Ready");
    }

    @Override
    public void onBeginningOfSpeech() {
        System.err.println("On Beginning");

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        System.err.println("On RMS change");

    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        System.err.println("On Buffer Received");
    }

    @Override
    public void onEndOfSpeech() {
        System.err.println("On End of Speech");
    }

    @Override
    public void onError(int error) {
        System.err.println("On Error: " + error);
        speechRecognizer.startListening(recognizerIntent);
    }

    @Override
    public void  onPartialResults(Bundle partialResults) {
        onResults(partialResults);
    }
    @Override
    public void  onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (matches.get(0).isEmpty()) {
            speechRecognizer.stopListening();
            speechRecognizer.destroy();
            speechRecognizer.startListening(recognizerIntent);
        }
        else {
            if (matches.get(0).equals("ready")) {
                imgBtnContinue.callOnClick();
            }
        }
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        System.err.println("On Event: " + eventType);
    }
}