package crysalis.example.yssa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoginBinding;
import services.YssaConnectionService;

public class LoginActivity extends AppCompatActivity {

    /* TODO:
    -openfire for messenger
    -openLdap for directory
    -mamp/mysql for external database
    -Room/SharedPreferences for internal storage of user credentials
    -all tasks and services will be run in this activity
    -need to write task in seperate intelliJ for bootstrapping order/load completion functions
    -need to write task in seperate intelliJ for bootstrapping retrieving workload from remote server
    -need to write task in seperate intelliJ for bootstrapping sending updates to remote server
    -Tomcat****
    -retrieve orders
    */

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //use if Managers want to send employees messages and vice versa
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        startService(new Intent(this, YssaConnectionService.class));
        etUsername = binding.etLoginUsername;
        etPassword = binding.etLoginPassword;
        btnLogin = binding.loginButton;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =  etUsername.getText().toString();
                String password =  etPassword.getText().toString();
            }
        });
    }
}