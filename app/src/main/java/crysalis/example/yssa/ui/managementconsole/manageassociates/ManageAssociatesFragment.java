package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import crysalis.example.yssa.R;
import pojos.Employee;

public class ManageAssociatesFragment extends Fragment implements View.OnClickListener {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    final String TAG = "ManageAssociates";
    private ManageAssociatesViewModel manageAssociatesViewModel;
    ManageAssociatesRecyclerViewAdapter adapter;
    Context context;
    ArrayList<Employee> employees;
    RecyclerView rvManageAssociates;
    ImageButton addUserButton;
    ImageButton deleteUserButton;
    ImageButton filterUserButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_manage_associates, container, false);
        context = getContext();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        addUserButton = root.findViewById(R.id.img_btn_add_user);
        addUserButton.setOnClickListener(this);
        deleteUserButton = root.findViewById(R.id.img_btn_delete_user);
        deleteUserButton.setOnClickListener(this);
        filterUserButton = root.findViewById(R.id.img_btn_filter_users);
//        rvManageAssociates = root.findViewById(R.id.rv_manage_associates);
//        rvManageAssociates.setAdapter(new ManageAssociatesRecyclerViewAdapter(employees));
//        rvManageAssociates.setLayoutManager(new LinearLayoutManager(context));
        return root;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_add_user:
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_input_add)
                        .setTitle(R.string.add_user)
                        .setView(R.layout.dialog_ui_add_user)
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // add to recyclerView etc
                                addUser("simon.mueller@levosonus.com", "password",
                                        "simon.mueller", "Simon Mueller", 8994);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .show();

                break;
            case R.id.img_btn_delete_user:
                deleteUser();
                break;
            default:
                break;
        }
    }


    public void addUser(String email, String password, String displayName, String fullName,
                        Integer employeeId) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
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
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void deleteUser() {

    }
}