package crysalis.example.yssa.ui.orderpicking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import crysalis.example.yssa.R;

public class OrderPickingActivity extends AppCompatActivity {

    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_picking);
        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.order_picking_container, new ReceiveInstructionFragment())
                .commit();
    }
}