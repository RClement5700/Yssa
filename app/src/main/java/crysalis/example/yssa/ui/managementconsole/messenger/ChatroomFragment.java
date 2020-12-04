package crysalis.example.yssa.ui.managementconsole.messenger;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentChatroomBinding;
import pojos.Employee;


public class ChatroomFragment extends Fragment implements View.OnClickListener {
    private final static String TAG = "Chatroom Fragment";
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    ArrayList<Employee> recipients;
    ImageButton imgBtnSend, imgBtnAddRecipients;
    RecyclerView rvChatroomMessages, rvDisplayRecipients;
    EditText etMessageBody;
    AlertDialog.Builder builder;
    View addRecipientsDialog;


    //Connect to database where chatroom is stored via websocket and set up websocket to push and pull
    //data from the database-chatroom whenever changes occur in the database
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    //API to READ/MANIPULATE inventory in a warehouse from INFOR/WMS3000 Trial/Forum etc
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentChatroomBinding binding = FragmentChatroomBinding.inflate(inflater, container,
                false);
        imgBtnAddRecipients = binding.imgBtnAddRecipients;
        imgBtnSend = binding.imgBtnSend;
        imgBtnAddRecipients.setOnClickListener(this);
        imgBtnSend.setOnClickListener(this);
        rvChatroomMessages = binding.rvChatroomMessages;
        rvDisplayRecipients = binding.rvDisplayRecipients;
        etMessageBody = binding.etMessageBody;
//        rvDisplayRecipients.setLayoutManager(new LinearLayoutManager(getContext()));
//        rvDisplayRecipients.setAdapter(new DisplayRecipientsRecyclerViewAdapter(getContext(),
//                recipients));
//        addRecipientsDialog = LayoutInflater.from(getContext()).inflate(R.layout.dialog_ui_select_associates,
//                (ViewGroup) getView(), false);
//        ProgressBar progressBarAddUser = addRecipientsDialog.findViewById(R.id.progress_bar_select_associates);
//        RecyclerView rvSelectAssociates = addRecipientsDialog.findViewById(R.id.rv_select_associates);
//        rvSelectAssociates.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_add_recipients:
//                builder.show();
                break;
            case R.id.img_btn_send:
                sendMessage();
                break;
        }
    }

    public void sendMessage() {
        String messageBody = etMessageBody.getText().toString();
        Timestamp time = new Timestamp(new Date());
        String sender = "users/8ZEhQnHY4WGeYuuNgY7e";
        String receiver = "users/6kR1RUzsund0WAZJSi2I";
        String chatroomId = "chatrooms/T36788SAfCPYdCqAQFJV";
        Map<String, Object> message = new HashMap<>();
        message.put("messageBody", messageBody);
        message.put("receiver", receiver);
        message.put("sender", sender);
        message.put("sentTime", time);
        message.put("chatroomId", chatroomId);
        if (!messageBody.isEmpty()) {
            //add message to Firestore
            mFirestore.collection("messages")
                    .add(message)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: "
                                    + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document: ", e);
                        }
                    });
        }
    }

}