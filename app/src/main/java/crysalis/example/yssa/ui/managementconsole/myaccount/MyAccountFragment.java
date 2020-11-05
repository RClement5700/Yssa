package crysalis.example.yssa.ui.managementconsole.myaccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentMyAccountBinding;
import pojos.MyFirebaseUser;

public class MyAccountFragment extends Fragment {

    private MyAccountViewModel myAccountViewModel;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser user;
    MyFirebaseUser currentUser;
    String email;
    String displayName;
    String employeeId;
    String fullName;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        myAccountViewModel =
                viewModelProvider.get(MyAccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_account, container, false);
        FragmentMyAccountBinding binding = FragmentMyAccountBinding.bind(root);
        final TextView tvWelcomeManagementEmployee = binding.welcomeManagementEmployee;
        final EditText etUpdateEmail = binding.etUpdateEmail;
        final EditText etUpdateDisplayName = binding.etUpdateDisplayName;
        final EditText etUpdatePassword = binding.etUpdatePassword;
        final EditText etUpdateProfilePicture = binding.etUpdateProfilePicture;
        final ImageView ivProfilePicture = binding.ivProfilePicture;
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance(mAuth.getApp());
        user = mAuth.getCurrentUser();
        currentUser = new MyFirebaseUser(user);
        tvWelcomeManagementEmployee.append("\n" + currentUser.getEmail());
        etUpdateDisplayName.setText(currentUser.getDisplayName());
        etUpdateEmail.setText(currentUser.getEmail());
        return binding.getRoot();
    }
}