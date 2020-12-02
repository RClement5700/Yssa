package crysalis.example.yssa.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentWelcomeBinding;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;
import crysalis.example.yssa.ui.managementconsole.manageassociates.ManageAssociatesRecyclerViewAdapter;
import crysalis.example.yssa.ui.managementconsole.messenger.Chatroom;
import crysalis.example.yssa.ui.managementconsole.messenger.ChatroomDao;
import crysalis.example.yssa.ui.managementconsole.messenger.ChatroomDatabase;
import crysalis.example.yssa.ui.managementconsole.messenger.ChatroomRepository;
import crysalis.example.yssa.ui.managementconsole.messenger.ChatroomViewModel;
import pojos.Employee;


public class WelcomeFragment extends Fragment implements View.OnClickListener {

    private final static String TAG = "Welcome Fragment";
    ChatroomDatabase chatroomdb;
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    FirebaseUser currentUser;
    Context context;
    Employee currentEmployee;
    TextView tvFullName;
    ProgressBar progressBar;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();
        context = getContext();
        chatroomdb = ChatroomDatabase.getDatabase(context);
        // Inflate the layout for this fragment
        FragmentWelcomeBinding binding = FragmentWelcomeBinding.inflate(inflater, container,
                false);
        View v = binding.getRoot();
        ImageView ivProfilePicture = binding.ivProfilePicture;
        ImageButton imgBtnContinue = binding.imgBtnContinue;
        tvFullName = binding.tvFullName;
        progressBar = binding.progressBar;
        buildCurrentEmployee();
        populateChatroomDb();
        imgBtnContinue.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        //check if management or associate
        Intent intent = new Intent(getActivity(),
                ManagementConsoleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.err.println("ChatroomId: "
//                        + chatroomdb.chatroomDao().getChatrooms().getValue().get(0).getRoomId());
//            }
//        });

    }

    public void buildCurrentEmployee() {
        mFirestore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d(TAG, "Retrieving Users Task Complete ", task.getException());
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String email = (String) document.get("email");
                                if (email.equals(currentUser.getEmail().toString())) {
                                    String fullName = (String) document.get("fullName");
                                    String displayName = (String) document.get("displayName");
                                    Long employeeId = (Long) document.get("employeeId");
                                    currentEmployee = new Employee(employeeId.intValue(), displayName, fullName);
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        tvFullName.setText(currentEmployee.getFullName());
                        //set profile picture here as well
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
    private void populateChatroomDb() {
        mFirestore.collection("chatrooms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "Retrieving Chatrooms Task Complete", task.getException());
                                for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                    Chatroom chatroom = new Chatroom(document.getId());
                                    chatroomdb.chatroomDao().insert(chatroom);
                                    System.err.println(TAG + " " +
                                            chatroomdb.chatroomDao().getChatroomsList().size()
                                    );
                                }
                            }
                        });
                    }
                });
    }
}