package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
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

import com.google.android.gms.internal.firebase_auth.zza;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapters.SpacesItemDecoration;
import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentManageAssociatesBinding;
import pojos.Employee;

public class ManageAssociatesFragment extends Fragment implements View.OnClickListener {

    final static String TAG = "ManageAssociates";
    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;
    Context context;
    RecyclerView rvManageAssociates;
    ImageButton addUserButton, deleteUserButton, filterUserButton;
    String email, password, displayName, fullName, currentUser;
    int employeeId;
    ArrayList<Employee> employees;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        employees = new ArrayList<Employee>();
        View root = inflater.inflate(R.layout.fragment_manage_associates, container, false);
        FragmentManageAssociatesBinding binding = FragmentManageAssociatesBinding.bind(root);
        addUserButton = binding.imgBtnAddUser;
        addUserButton.setOnClickListener(this);
        deleteUserButton = binding.imgBtnDeleteUser;
        deleteUserButton.setOnClickListener(this);
        filterUserButton = binding.imgBtnFilterUsers;
        currentUser = mAuth.getCurrentUser().getEmail();
        rvManageAssociates = binding.rvManageAssociates;
        rvManageAssociates.setLayoutManager(new LinearLayoutManager(context));
        retrieveUsers();
        return root;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_add_user:
                buildUser();
                break;
            case R.id.img_btn_delete_user:
                deleteUser();
                break;
            default:
                break;
        }
    }

    public void retrieveUsers() {
        mFirestore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d(TAG, "Retrieving Users Task Complete ", task.getException());
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Num of Docs: " +
                                    task.getResult().getDocuments().size());
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String email = (String) document.get("email");
                                String fullName = (String) document.get("fullName");
                                String displayName = (String) document.get("displayName");
                                Long employeeId = (Long) document.get("employeeId");
                                Log.d(TAG, "Email: " + email);
                                Employee employee = new Employee(employeeId.intValue(), displayName, fullName);
                                employees.add(employee);
                            }
                            rvManageAssociates.setAdapter(
                                    new ManageAssociatesRecyclerViewAdapter(employees, mFirestore));
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void buildUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add User");
        builder.setIcon(android.R.drawable.ic_input_add);
        View addUserDialog = LayoutInflater.from(getContext()).inflate(R.layout.dialog_ui_add_user,
                (ViewGroup) getView(), false);
        final ProgressBar progressBar = addUserDialog.findViewById(R.id.progress_bar_new_user);
        progressBar.setVisibility(View.GONE);
        final EditText inputEmail = addUserDialog.findViewById(R.id.et_enter_email);
        final EditText inputPassword = addUserDialog.findViewById(R.id.et_enter_password);
        final EditText inputDisplayName = addUserDialog.findViewById(R.id.et_enter_display_name);
        final EditText inputFullName = addUserDialog.findViewById(R.id.et_enter_full_name);
        final EditText inputEmployeeId = addUserDialog.findViewById(R.id.et_enter_employee_id);
        inputEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        inputPassword.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
        inputDisplayName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        inputFullName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        inputEmployeeId.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        builder.setView(addUserDialog);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressBar.setVisibility(View.VISIBLE);
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();
                displayName = inputDisplayName.getText().toString();
                fullName = inputFullName.getText().toString();
                String stringEmployeeId = inputEmployeeId.getText().toString();
                employeeId = Integer.parseInt(stringEmployeeId);
                addUser(email, password, displayName, fullName, employeeId);
                progressBar.setVisibility(View.GONE);
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void authenticateUser() {
        mFirestore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String verifyEmail = (String) document.getData().get("email");
                                if (verifyEmail.equals(currentUser)) {
                                    Log.d(TAG, document.getId() + " => " + document.getData().get("email"));
                                    String currentPassword = (String) document.getData().get("password");
                                    mAuth.signInWithEmailAndPassword(currentUser, currentPassword);
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    private void addUser(final String email, String password, final String displayName, String fullName,
                         final Integer employeeId) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            /*
                            TODO:
                                -update new user credentials at runtime didn't work
                                instead, follow ManageUsers tutorial from
                                https://firebase.google.com/docs/auth/admin/manage-users#java_4
                                -research offline speech recognition
                                -research openfire vs firebase
                             */

                            mAuth.signOut();
                            authenticateUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Map<String, Object> user = new HashMap<>();
        user.put("displayName", displayName);
        user.put("email", email);
        user.put("employeeId", employeeId);
        user.put("fullName", fullName);
        user.put("password",password);
        mFirestore.collection("users")
                .add(user)
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

    public void deleteUser() {
    }
}