package crysalis.example.yssa.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecognitionListener {

    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    ImageButton imgBtnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        imgBtnContinue = binding.imgBtnContinue;
        imgBtnContinue.setOnClickListener(this);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);
        speechRecognizerIntent = new Intent();
        speechRecognizer.startListening(speechRecognizerIntent);
        setContentView(view);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, LoginRegisterActivity.class));
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (String s: matches) {
            if(s.equals("ready") || s.equals("Ready")) {
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                imgBtnContinue.callOnClick();
            }
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}