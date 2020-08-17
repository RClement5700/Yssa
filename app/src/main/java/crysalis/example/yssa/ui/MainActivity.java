package crysalis.example.yssa.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

/* TODO:
    -openfire for userdata base
    -Room for internal storage of user credentials etc
    -all tasks and services will be run in this activity
    -Don't replace fragments when buttons are pushed; addToBackStack instead and update back button
        accordingly
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragments_container, new LoginFragment())
                .commit();
        binding.fragmentsContainer.bringToFront();
    }
}