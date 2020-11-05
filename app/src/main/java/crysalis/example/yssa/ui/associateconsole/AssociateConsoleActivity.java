package crysalis.example.yssa.ui.associateconsole;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityAssociateConsoleBinding;
import crysalis.example.yssa.ui.associateconsole.chooserole.ChooseRoleFragment;

public class AssociateConsoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associate_console);
        ActivityAssociateConsoleBinding binding = ActivityAssociateConsoleBinding.inflate(getLayoutInflater());
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.associate_console_container, new ChooseRoleFragment())
                .addToBackStack(null)
                .commit();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}