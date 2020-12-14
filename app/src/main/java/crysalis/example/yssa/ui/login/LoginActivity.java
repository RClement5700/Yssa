package crysalis.example.yssa.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername;
    EditText etPassword;
    ProgressBar loading;
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    final String TAG = "Login Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        etUsername = view.findViewById(R.id.username);
        etPassword = view.findViewById(R.id.password);
        loading = view.findViewById(R.id.loading);
        ImageButton imgBtnLogin = view.findViewById(R.id.img_btn_login);
        imgBtnLogin.setOnClickListener(this);
        setContentView(view);
    }

    @Override
    public void onClick(View v) {
        login();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void login() {

    }
}