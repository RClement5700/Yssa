package crysalis.example.yssa.ui.orderpicking;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentReceiveInstructionBinding;
import pojos.Order;

public class ReceiveInstructionFragment extends Fragment implements View.OnClickListener {

    FirebaseFirestore mFirestore;
    ObjectAnimator pulseAnimation;
    ImageButton imgBtnListening;
    ImageButton imgBtnComplete;
    Order order;
    static final String TAG = "Receive Instructions Fragment";

    public ReceiveInstructionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentReceiveInstructionBinding binding = FragmentReceiveInstructionBinding.inflate(inflater);
        View v = binding.getRoot();
        mFirestore = FirebaseFirestore.getInstance();
        imgBtnComplete = binding.imgBtnCompleted;
        imgBtnComplete.setOnClickListener(this);
        imgBtnListening = binding.imgBtnListening;
        imgBtnListening.setOnClickListener(this);
        pulseAnimation = ObjectAnimator.ofPropertyValuesHolder(
                imgBtnListening,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        pulseAnimation.setDuration(310);
        pulseAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        pulseAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_listening:
                if (pulseAnimation.isStarted()) {
                    pulseAnimation.cancel();
                } else {
                    pulseAnimation.start();
                }
            case R.id.img_btn_completed:
//                ((OrderPickingActivity) getActivity()).retrieveNextOrder();

        }
    }

    public void nextJob() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="https://10.0.2.2/localhost:8888/yssa/orders";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
