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

public class LoadingActivity extends AppCompatActivity {

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
//        mAuth = FirebaseAuth.getInstance();
//        mFirestore = FirebaseFirestore.getInstance();
////        chatroomdb = ChatroomDatabase.getDatabase(context);
//        ActivityLoadingBinding binding = ActivityLoadingBinding.inflate(getLayoutInflater());
//        ImageView ivProfilePicture = binding.ivProfilePicture;
//        ImageButton imgBtnContinue = binding.imgBtnContinue;
//        tvFullName = binding.tvFullName;
//        progressBar = binding.progressBar;
//        imgBtnContinue.setOnClickListener(this);
////        buildCurrentEmployee();
////        populateChatroomDb();
//        setContentView(binding.getRoot());
    }
}