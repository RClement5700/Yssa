package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentManageAssociatesBinding;
import pojos.Employee;

public class ManageAssociatesFragment extends Fragment implements View.OnClickListener {

    final static String TAG = "ManageAssociates";
    ManageAssociatesViewModel manageAssociatesViewModel;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    Context context;
    FragmentManageAssociatesBinding binding;
    ArrayList<Employee> employees;
    RecyclerView rvManageAssociates;
    ManageAssociatesRecyclerViewAdapter adapter;
    ImageButton addUserButton, deleteUserButton, filterUserButton;
    String email, password, displayName, fullName;
    int employeeId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        View root = inflater.inflate(R.layout.fragment_manage_associates, container, false);
        binding = FragmentManageAssociatesBinding.bind(root);
        addUserButton = binding.imgBtnAddUser;
        addUserButton.setOnClickListener(this);
        deleteUserButton = binding.imgBtnDeleteUser;
        deleteUserButton.setOnClickListener(this);
        filterUserButton = binding.imgBtnFilterUsers;

//        rvManageAssociates = root.findViewById(R.id.rv_manage_associates);
//        rvManageAssociates.setAdapter(new ManageAssociatesRecyclerViewAdapter(employees));
//        rvManageAssociates.setLayoutManager(new LinearLayoutManager(context));
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

    public void buildUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add User");
        builder.setIcon(android.R.drawable.ic_input_add);
        View addUserDialog = LayoutInflater.from(getContext()).inflate(R.layout.dialog_ui_add_user,
                (ViewGroup) getView(), false);
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
                email = inputEmail.getText().toString();
                password = inputPassword.getText().toString();
                displayName = inputDisplayName.getText().toString();
                fullName = inputFullName.getText().toString();
                String stringEmployeeId = inputEmployeeId.getText().toString();
                employeeId = Integer.parseInt(stringEmployeeId);
                addUser(email, password, displayName, fullName, employeeId);
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


    private void addUser(String email, String password, String displayName, String fullName,
                        Integer employeeId) {
        final String currentEmail = mAuth.getCurrentUser().getEmail();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            mAuth.signOut();
                            DocumentReference docRef = db.collection("users").document("8ZEhQnHY4WGeYuuNgY7e");
                            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                            String currentPassword = document.getString("password");
                                            mAuth.signInWithEmailAndPassword(currentEmail, currentPassword);
                                        } else {
                                            Log.d(TAG, "No such document");
                                        }
                                    } else {
                                        Log.d(TAG, "get failed with ", task.getException());
                                    }
                                }
                            });
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
        db.collection("users")
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