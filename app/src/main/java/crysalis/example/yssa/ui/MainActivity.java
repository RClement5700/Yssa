package crysalis.example.yssa.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

/* TODO:
    openfire for userdata base
    Room for internal storage of user credentials etc
    all tasks and services will be run in this activity
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragments_container, new LoginFragment())
                .commit();
        binding.fragmentsContainer.bringToFront();
    }
}