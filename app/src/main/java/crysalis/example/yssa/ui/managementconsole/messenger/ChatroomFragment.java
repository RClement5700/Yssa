package crysalis.example.yssa.ui.managementconsole.messenger;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firestore.v1.DocumentTransform;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentChatroomBinding;
import crysalis.example.yssa.ui.managementconsole.manageassociates.ManageAssociatesRecyclerViewAdapter;
import pojos.Employee;
import pojos.Message;


public class ChatroomFragment extends Fragment implements View.OnClickListener {
    private final static String TAG = "Chatroom Fragment";
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    ArrayList<Employee> recipients;
    ImageButton imgBtnSend, imgBtnAddRecipients;
    RecyclerView rvChatroomMessages, rvDisplayRecipients;
    EditText etMessageBody;
    AlertDialog.Builder builder;


    //Connect to database where chatroom is stored via websocket and set up websocket to push and pull
    //data from the database-chatroom whenever changes occur in the database
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        recipients = new ArrayList<>();
        View addRecipientsDialog = LayoutInflater.from(getContext()).inflate(R.layout.dialog_ui_select_associates,
                (ViewGroup) getView(), false);
        final ProgressBar progressBarNewUser = addRecipientsDialog.findViewById(R.id.progress_bar_select_associates);
        final RecyclerView rvSelectAssociates = addRecipientsDialog.findViewById(R.id.rv_select_associates);
        final ManageAssociatesRecyclerViewAdapter adapter = new ManageAssociatesRecyclerViewAdapter(progressBarNewUser);
        rvSelectAssociates.setAdapter(adapter);
        rvSelectAssociates.setLayoutManager(new LinearLayoutManager(getActivity()));
        builder = new AlertDialog.Builder(getContext())
                .setTitle("Add Recipients")
                .setIcon(android.R.drawable.ic_input_add)
                .setView(addRecipientsDialog)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressBarNewUser.setVisibility(View.VISIBLE);
                        recipients.addAll(adapter.getSelectedEmployees());
                        progressBarNewUser.setVisibility(View.GONE);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }

    //API to READ/MANIPULATE inventory in a warehouse from INFOR/WMS3000 Trial/Forum etc
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentChatroomBinding binding = FragmentChatroomBinding.inflate(inflater, container,
                false);
        imgBtnAddRecipients = binding.imgBtnAddRecipients;
        imgBtnAddRecipients.setOnClickListener(this);
        imgBtnSend = binding.imgBtnSend;
        rvChatroomMessages = binding.rvChatroomMessages;
        rvDisplayRecipients = binding.rvDisplayRecipients;
        etMessageBody = binding.etMessageBody;
        rvDisplayRecipients.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDisplayRecipients.setAdapter(new DisplayRecipientsRecyclerViewAdapter(getContext(),
                recipients));
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_add_recipients:
                builder.show();
                break;
            case R.id.img_btn_send:
                Context context;
                break;
            default:
                break;
        }
    }

    public void sendMessage() {
        String messageBody = etMessageBody.getText().toString();
        Timestamp time = new Timestamp(new Date());
        String sender = "users/8ZEhQnHY4WGeYuuNgY7e";
        String receiver = "users/6kR1RUzsund0WAZJSi2I";
        Map<String, Object> message = new HashMap<>();
        message.put("messageBody", messageBody);
        message.put("receiver", receiver);
        message.put("sender", sender);
        message.put("sentTime", time);

        //add message to Firestore
        mFirestore.collection("users")
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