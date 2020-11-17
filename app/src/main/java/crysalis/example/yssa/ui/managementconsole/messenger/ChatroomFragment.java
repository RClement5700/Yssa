package crysalis.example.yssa.ui.managementconsole.messenger;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentChatroomBinding;
import crysalis.example.yssa.ui.managementconsole.manageassociates.ManageAssociatesRecyclerViewAdapter;
import pojos.Employee;


public class ChatroomFragment extends Fragment {
    FirebaseFirestore mFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFirestore = FirebaseFirestore.getInstance();
        View root = inflater.inflate(R.layout.fragment_chatroom, container, false);
        FragmentChatroomBinding binding = FragmentChatroomBinding.bind(root);
        return binding.getRoot();
    }

    public void retrieveUsers() {
        progressBarLoadAssociates.setVisibility(View.VISIBLE);
        mFirestore.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d(TAG, "Retrieving Users Task Complete ", task.getException());
                        if (task.isSuccessful()) {
                            for (int i = 0; i < employees.size(); i++) employees.remove(i);
                            Log.d(TAG, "Num of Docs: " +
                                    task.getResult().getDocuments().size());
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String email = (String) document.get("email");
                                String fullName = (String) document.get("fullName");
                                String displayName = (String) document.get("displayName");
                                Long employeeId = (Long) document.get("employeeId");
                                Employee employee = new Employee(employeeId.intValue(), displayName, fullName);
                                employees.add(employee);
                            }
                            rvManageAssociates.setAdapter(
                                    new ManageAssociatesRecyclerViewAdapter(employees, mFirestore));
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            Toast.makeText(getContext(),
                                    "Error retrieving users", Toast.LENGTH_LONG).show();
                        }
                        progressBarLoadAssociates.setVisibility(View.GONE);
                    }
                });
    }

}