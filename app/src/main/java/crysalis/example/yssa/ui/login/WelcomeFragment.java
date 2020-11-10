package crysalis.example.yssa.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentWelcomeBinding;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;
import crysalis.example.yssa.ui.managementconsole.manageassociates.ManageAssociatesRecyclerViewAdapter;
import pojos.Employee;


public class WelcomeFragment extends Fragment {

    private final static String TAG = "Welcome Fragment";
    FirebaseFirestore mFirestore;
    FirebaseUser currentUser;
    Employee currentEmployee;
    TextView tvFullName;
    ProgressBar progressBar;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();
        // Inflate the layout for this fragment
        FragmentWelcomeBinding binding = FragmentWelcomeBinding.inflate(inflater, container,
                false);
        View v = binding.getRoot();
        ImageView ivProfilePicture = binding.ivProfilePicture;
        ImageButton imgBtnContinue = binding.imgBtnContinue;
        tvFullName = binding.tvFullName;
        progressBar = binding.progressBar;
        buildCurrentEmployee();
        imgBtnContinue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //check if management or associate
                Intent intent = new Intent(getActivity(),
                        ManagementConsoleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        return v;
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
}