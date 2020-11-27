package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Employee;

public class ManageAssociatesRecyclerViewAdapter extends
        RecyclerView.Adapter<ManageAssociatesRecyclerViewAdapter.ManageAssociatesRecyclerViewHolder> {

    private final static String TAG = "Firebase Users";
    ArrayList<Employee> employees;
    ArrayList<Employee> selectedEmployees;
    FirebaseFirestore mFirestore;
    ProgressBar progressBarLoadAssociates;

    public ManageAssociatesRecyclerViewAdapter(ProgressBar progressBarLoadAssociates) {
        this.progressBarLoadAssociates = progressBarLoadAssociates;
        mFirestore = FirebaseFirestore.getInstance();
        employees = new ArrayList<Employee>();
        selectedEmployees = new ArrayList<Employee>();
        retrieveUsers();
    }

    @Override
    public ManageAssociatesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_manage_associates,
                parent, false);
        return new ManageAssociatesRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ManageAssociatesRecyclerViewHolder holder, int position) {
        String employeeId = "" + employees.get(position).getEmployeeId();
        String username = employees.get(position).getUsername();
        String fullName = employees.get(position).getFullName();

        holder.tvUserId.setText(employeeId);
        holder.tvUsername.setText(username);
        holder.tvFullName.setText(fullName);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedEmployees.add(employees.get(position));
                }
                if (!isChecked) {
                    selectedEmployees.remove(employees.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public ArrayList<Employee> getSelectedEmployees() {
        return selectedEmployees;
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
                            notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        progressBarLoadAssociates.setVisibility(View.GONE);
                    }
                });
    }


    static class ManageAssociatesRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId;
        TextView tvUsername;
        TextView tvFullName;
        CheckBox checkBox;

        public ManageAssociatesRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = (TextView) itemView.findViewById(R.id.tv_associate_name);
            tvUserId = (TextView) itemView.findViewById(R.id.tv_associate_user_id);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_associate_username);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox_manage_associate);
        }
    }
}
