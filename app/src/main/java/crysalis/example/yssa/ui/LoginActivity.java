package crysalis.example.yssa.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoginBinding;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;
import pojos.Employee;

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
        mAuth.signOut();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void loginComplete() {
        Intent intent = new Intent(LoginActivity.this,
                ManagementConsoleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        System.err.println("currentuser: " +mAuth.getCurrentUser());
        startActivity(intent);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        loginComplete();
//    }

    public void login() {
        System.err.println("project id: " +
                FirebaseApp.getInstance().getOptions().getProjectId());
        System.err.println("this project id: " +
                mAuth.getApp().getOptions().getProjectId());
        System.err.println("API key: " + mAuth.getApp().getOptions().getApiKey());
        mAuth.signInWithEmailAndPassword("rohan.clement@levosonus.com",
                "yssa.clement2020")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.err.println("Signing in...");
                            loginComplete();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            System.err.println(task.getException());
//                            updateUI(null);
                        }
                    }
                });
    }
}