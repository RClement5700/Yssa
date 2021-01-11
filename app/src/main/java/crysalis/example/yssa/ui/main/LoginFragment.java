package crysalis.example.yssa.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentLoginBinding;
import crysalis.example.yssa.ui.associateconsole.AssociateConsoleActivity;

public class LoginFragment extends Fragment implements View.OnClickListener, RecognitionListener {

    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    EditText etEmployeeId;
    ProgressBar progressBar;
    ImageButton imgBtnContinue;
    TextView one;
    TextView two;
    TextView three;
    TextView four;
    TextView five;
    TextView six;
    TextView seven;
    TextView eight;
    TextView nine;
    TextView zero;
    static String employeeId;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    final static String TAG = "Login Activity: ";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        speechRecognizerIntent = new Intent();
        etEmployeeId = binding.etEnterEmployeeId;
        progressBar = binding.progressBar;
        imgBtnContinue = binding.imgBtnContinue;
        one = binding.one;
        two = binding.two;
        three = binding.three;
        four = binding.four;
        five = binding.five;
        six = binding.six;
        seven = binding.seven;
        eight = binding.eight;
        nine = binding.nine;
        zero = binding.zero;
        imgBtnContinue.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        speechRecognizer.setRecognitionListener(this);
        speechRecognizer.startListening(speechRecognizerIntent);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_continue:
                login();
                progressBar.setVisibility(View.GONE);
                break;
            case R.id.one:
                etEmployeeId.append("1");
                break;
            case R.id.two:
                etEmployeeId.append("2");
                break;
            case R.id.three:
                etEmployeeId.append("3");
                break;
            case R.id.four:
                etEmployeeId.append("4");
                break;
            case R.id.five:
                etEmployeeId.append("5");
                break;
            case R.id.six:
                etEmployeeId.append("6");
                break;
            case R.id.seven:
                etEmployeeId.append("7");
                break;
            case R.id.eight:
                etEmployeeId.append("8");
                break;
            case R.id.nine:
                etEmployeeId.append("9");
                break;
            case R.id.zero:
                etEmployeeId.append("0");
                break;
        }

    }


    //uncomment mFirestore section when ready to show
    public void login() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.bringToFront();
        employeeId = etEmployeeId.getText().toString();
        Intent intent = new Intent(getActivity(), AssociateConsoleActivity.class);
        startActivity(intent);
//        mFirestore.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                List<DocumentSnapshot> userDocuments = task.getResult().getDocuments();
//                boolean isAuthenticated = false;
//                for (int i = 0; i < userDocuments.size(); i++) {
//                    String currentId = userDocuments.get(i).getId();
//                    if (currentId.matches(etEmployeeId.getText().toString())) {
//                        employeeId = currentId;
//                        isAuthenticated = true;
//                        System.err.println(TAG + employeeId);
//                        SharedPreferences preferences = getPreferences(0);
//                        SharedPreferences.Editor editor = preferences.edit();
//                        editor.putString("employeeId", employeeId);
//                        editor.apply();
////                        Intent intent = new Intent(LoginActivity.this,
////                                AssociateConsoleActivity.class);
////                        startActivity(intent);
//                        break;
//                    }
//                }
//                if (!isAuthenticated) {
//                    etEmployeeId.setText("");
//                    Toast.makeText(context, "Authentication failed...", Toast.LENGTH_SHORT)
//                            .show();
//                }
//                progressBar.setVisibility(View.GONE);
//            }
//        });
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
            if (s.equals("ready")) {
                imgBtnContinue.callOnClick();
            }
            if(s.equals("9")) {
                nine.callOnClick();
            }
            if(s.equals("8")) {
                eight.callOnClick();
            }
            if(s.equals("7")) {
                seven.callOnClick();
            }
            if(s.equals("6")) {
                six.callOnClick();
            }
            if(s.equals("5")) {
                five.callOnClick();
            }
            if(s.equals("4")) {
                four.callOnClick();
            }
            if(s.equals("3")) {
                three.callOnClick();
            }
            if(s.equals("2")) {
                two.callOnClick();
            }
            if(s.equals("1")) {
                one.callOnClick();
            }
            if(s.equals("0")) {
                zero.callOnClick();
            }
            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            speechRecognizer.startListening(speechRecognizerIntent);
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}