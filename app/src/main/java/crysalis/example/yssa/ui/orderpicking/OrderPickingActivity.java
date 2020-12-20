package crysalis.example.yssa.ui.orderpicking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import crysalis.example.yssa.R;
import pojos.Employee;
import pojos.Order;

public class OrderPickingActivity extends AppCompatActivity implements View.OnClickListener {

    //products array in firestore are a Maps<K,V> where K = productId && V = quantity to be picked
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    FragmentManager fm;
    FloatingActionButton fabOpenListeningFragment;
    FloatingActionButton fabOpenMessengerFragment;


    final String TAG = "Order Picking Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_picking);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        fm = getSupportFragmentManager();
        fabOpenListeningFragment = findViewById(R.id.fab_open_listener_fragment);
        fabOpenMessengerFragment = findViewById(R.id.fab_open_messages);
        fabOpenListeningFragment.setOnClickListener(this);
        fabOpenMessengerFragment.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_open_listener_fragment:
                fm.beginTransaction()
                        .add(R.id.order_picking_container, new ReceiveInstructionFragment())
                        .commit();
            case R.id.fab_open_messages:
                //open messages
        }
    }
}