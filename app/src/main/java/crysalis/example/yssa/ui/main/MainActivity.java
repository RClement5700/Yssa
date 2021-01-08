package crysalis.example.yssa.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgBtnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        imgBtnContinue = binding.imgBtnContinue;
        imgBtnContinue.setOnClickListener(this);
        setContentView(view);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, LoginRegisterActivity.class));
    }
}