package crysalis.example.yssa.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityMainBinding;
import crysalis.example.yssa.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        ImageButton imgButtonContinue = view.findViewById(R.id.img_btn_continue);
        imgButtonContinue.setOnClickListener(this);
        setContentView(view);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}