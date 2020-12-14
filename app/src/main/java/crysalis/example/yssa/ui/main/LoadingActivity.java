package crysalis.example.yssa.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.ActivityLoadingBinding;
import crysalis.example.yssa.databinding.ActivityLoginBinding;
import crysalis.example.yssa.databinding.FragmentWelcomeBinding;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;
import crysalis.example.yssa.ui.managementconsole.messenger.Chatroom;
import crysalis.example.yssa.ui.managementconsole.messenger.ChatroomDatabase;
import pojos.Employee;

public class LoadingActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "Welcome Fragment";
    ChatroomDatabase chatroomdb;
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    FirebaseUser currentUser;
    Context context;
    Employee currentEmployee;
    TextView tvFullName;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();
        chatroomdb = ChatroomDatabase.getDatabase(context);
        ActivityLoadingBinding binding = ActivityLoadingBinding.inflate(getLayoutInflater());
        ImageView ivProfilePicture = binding.ivProfilePicture;
        ImageButton imgBtnContinue = binding.imgBtnContinue;
        tvFullName = binding.tvFullName;
        progressBar = binding.progressBar;
        context = this;
        imgBtnContinue.setOnClickListener(this);
        buildCurrentEmployee();
        populateChatroomDb();
        setContentView(binding.getRoot());
    }

    @Override
    public void onClick(View v) {
        //check if management or associate
        Intent intent = new Intent(LoadingActivity.this,
                ManagementConsoleActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



    //store credentials in SharedPreferences rather than reusing this method in multiple Fragments
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