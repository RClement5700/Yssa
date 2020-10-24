package crysalis.example.yssa.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoginBinding;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    ProgressBar loading;
    Button loginBtn;
    FirebaseAuth mAuth;
    final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        loading = (ProgressBar) findViewById(R.id.loading);
        loading.setVisibility(View.GONE);
        loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                String email = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(email, password);
            }
        });
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

    public void loginComplete() {
        Intent intent = new Intent(LoginActivity.this,
                ManagementConsoleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        loading.setVisibility(View.GONE);
        startActivity(intent);
    }

    private void loginHelper(String email, String password) {
        System.err.println("Signing in...");
        System.err.println("project id: " +
                FirebaseApp.getInstance().getOptions().getProjectId());
        System.err.println("this project id: " +
                mAuth.getApp().getOptions().getProjectId());
        System.err.println("API key: " + mAuth.getApp().getOptions().getApiKey());
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