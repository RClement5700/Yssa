package crysalis.example.yssa.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityEquipmentBinding;


//TODO: This class should be a fragment
public class EquipmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityEquipmentBinding binding = ActivityEquipmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        final FragmentManager fm = getSupportFragmentManager();
        binding.forkliftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .add(R.id.equipment_fragment_container, new ForkliftHomescreenFragment())
                        .commit();
                binding.equipmentFragmentContainer.bringToFront();
            }
        });
        binding.electricPalletJackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .add(R.id.equipment_fragment_container, new ElectricPalletJackHomescreenFragment())
                        .commit();
                binding.equipmentFragmentContainer.bringToFront();
            }
        });
    }
}