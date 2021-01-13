package crysalis.example.yssa.ui.main;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tomer.fadingtextview.FadingTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentVoiceProfileBinding;

public class VoiceProfileFragment extends Fragment implements View.OnClickListener, RecognitionListener, TextToSpeech.OnInitListener {
//        TODO:
//        new user will be prompted to create voice profile
//        VoiceEngine will prompt new user for the same word repeatedly; after 5 results are recorded
//        those 5 results will make up the attributes of the document being created in firestore
//        ex: Document: eight
//                  attribute: "eight"
//                  attribute: "hey"
//                  attribute: "ace"
//                  attribute: "ape"
//                  attribute: "ache"
//        after all words have been recorded, VoiceEngine will send VoiceProfile to Firestore
    FadingTextView tvCreateVoiceProfile;
    TextView tvRepeatTheFollowing;
    ImageButton imgBtnMic;
    SpeechRecognizer speechRecognizer;
    Intent speechRecognitionIntent;
    FirebaseFirestore mFirestore;
    private String UID;
    boolean creatingVoiceProfile = false;
    int[] staticTemplate = {
            R.string.one, R.string.two, R.string.three,
            R.string.four, R.string.five, R.string.six,
            R.string.seven, R.string.eight, R.string.nine,
            R.string.zero
    };
    public VoiceProfileFragment(String UID) {
        this.UID = UID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentVoiceProfileBinding binding = FragmentVoiceProfileBinding.inflate(inflater);
        View v = binding.getRoot();
        mFirestore = FirebaseFirestore.getInstance();
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        speechRecognizer.setRecognitionListener(this);
        speechRecognitionIntent = new Intent();
        speechRecognizer.startListening(speechRecognitionIntent);
        tvCreateVoiceProfile = binding.tvCreateVoiceProfile;
        tvRepeatTheFollowing = binding.tvRepeatTheFollowing;
        imgBtnMic = binding.imgBtnMicrophone;
        imgBtnMic.setOnClickListener(this);
        String[] prompts = {getString(R.string.prompt_create_voice_profile),
                getString(R.string.prompt_press_button)};
        tvCreateVoiceProfile.setTexts(prompts);
        tvCreateVoiceProfile.restart();
        return v;
    }

    public void createVoiceProfile(ArrayList<String> prompts) {
        TextToSpeech tts = new TextToSpeech(getActivity(), this);
        tts.setLanguage(Locale.US);
        tts.speak("1", TextToSpeech.QUEUE_ADD, new Bundle(), TextToSpeech.ACTION_TTS_QUEUE_PROCESSING_COMPLETED);
        for (String s: prompts) {
            Map<String, String> template = new HashMap<>(); //<prompt, input>
        }
        mFirestore.collection("voiceProfiles").document(UID).set(template)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    @Override
    public void onInit(int status) {

    }

    @Override
    public void onStart() {
        super.onStart();
        speechRecognizer.startListening(speechRecognitionIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        speechRecognizer.startListening(speechRecognitionIntent);
    }

    @Override
    public void onPause() {
        super.onPause();
        speechRecognizer.stopListening();
        speechRecognizer.destroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        speechRecognizer.stopListening();
        speechRecognizer.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        speechRecognizer.stopListening();
        speechRecognizer.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        speechRecognizer.stopListening();
        speechRecognizer.destroy();
    }


    @Override
    public void onClick(View v) {
        creatingVoiceProfile = true;
        Toast.makeText(getActivity(), "Preparing Voice Template", Toast.LENGTH_SHORT).show();
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
        speechRecognizer.startListening(speechRecognitionIntent);
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (matches.get(0).equals("ready")) {
            imgBtnMic.callOnClick();
        }
        else {
            speechRecognizer.stopListening();
            speechRecognizer.destroy();
            speechRecognizer.startListening(speechRecognitionIntent);
        }
        while (creatingVoiceProfile) {
            tvRepeatTheFollowing.setVisibility(View.VISIBLE);
            ArrayList<String> voiceTemplate = new ArrayList<>();
            for (int current : staticTemplate) {
                int count = 5;
                while (count > 0) {
                    voiceTemplate.add(getString(current));
                    count--;
                }
            }
            String[] voiceTemplateArray = new String[voiceTemplate.size()];
            voiceTemplateArray = voiceTemplate.toArray(voiceTemplateArray);
            tvCreateVoiceProfile.setTimeout(3, TimeUnit.SECONDS);
            tvCreateVoiceProfile.setTexts(voiceTemplateArray);
            creatingVoiceProfile = false;
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        onResults(partialResults);

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
