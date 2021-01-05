package crysalis.example.yssa.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.List;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoginBinding;
import crysalis.example.yssa.ui.associateconsole.AssociateConsoleActivity;
import crysalis.example.yssa.ui.main.LoadingActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    Context context;
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
    final static String TAG = "Login Activity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        context = this;
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
        setContentView(binding.getRoot());
    }

    @Override
    public void onStart() {
        super.onStart();
        //if logged in, continue login
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_continue:
                login();
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
        Intent intent = new Intent(LoginActivity.this,
                AssociateConsoleActivity.class);
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
}