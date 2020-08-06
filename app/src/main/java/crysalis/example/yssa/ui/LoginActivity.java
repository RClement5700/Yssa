package crysalis.example.yssa.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(LoginActivity.this, EquipmentActivity.class));
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .add(R.id.choose_department_container, new ChooseDepartmentFragment())
                        .commit();
                binding.chooseDepartmentContainer.bringToFront();
            }
        });

    }
}