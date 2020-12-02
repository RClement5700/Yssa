package crysalis.example.yssa.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    ProgressBar loading;
    FirebaseAuth mAuth;
    final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        etUsername = view.findViewById(R.id.username);
        etPassword = view.findViewById(R.id.password);
        loading = view.findViewById(R.id.loading);
        ImageButton imgBtnLogin = view.findViewById(R.id.img_btn_login);
        imgBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                String email = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(email, password);
            }
        });
        setContentView(view);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            etUsername.setText(currentUser.getEmail());
        }
    }

    private void loginComplete() {
        View v = findViewById(R.id.welcome_container);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.welcome_container, new WelcomeFragment())
                .commit();
        loading.setVisibility(View.GONE);
        v.bringToFront();
    }

    private void loginHelper(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,
                password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginComplete();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            System.err.println(task.getException());
                        }
                    }
                });
        loading.setVisibility(View.GONE);
    }

    public void login(String email, String password) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            loginComplete();
        }
        else {
            if (email == null | email.isEmpty()) {
                Toast.makeText(this, "Must enter email...", Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
            }
            else if (password == null | password.isEmpty()) {
                Toast.makeText(this, "Must enter password...", Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
            }
            else {
                loginHelper(email, password);
            }
        }
    }
}