package crysalis.example.yssa.ui.orderpicking;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import crysalis.example.yssa.R;

public class OrderPickingActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fm;
    FloatingActionButton fabOpenListeningFragment;
    FloatingActionButton fabOpenMessengerFragment;


    final String TAG = "Order Picking Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_picking);
        fm = getSupportFragmentManager();
        fabOpenListeningFragment = findViewById(R.id.fab_open_listener_fragment);
        fabOpenMessengerFragment = findViewById(R.id.fab_open_messages);
        fabOpenListeningFragment.setOnClickListener(this);
        fabOpenMessengerFragment.setOnClickListener(this);
        fm.beginTransaction()
                .add(R.id.orderPickingContainer, new CurrentOrderFragment())
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_open_listener_fragment:
//                fm.beginTransaction()
//                        .add(R.id.order_picking_container, new ReceiveInstructionFragment())
//                        .commit();
            case R.id.fab_open_messages:
                //open messages
        }
    }


}