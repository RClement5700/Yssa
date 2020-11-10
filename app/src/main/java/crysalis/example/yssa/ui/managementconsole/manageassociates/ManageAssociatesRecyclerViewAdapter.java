package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Employee;

public class ManageAssociatesRecyclerViewAdapter extends
        RecyclerView.Adapter<ManageAssociatesRecyclerViewAdapter.ManageAssociatesRecyclerViewHolder> {

    private final static String TAG = "Firebase Users";
    ArrayList<Employee> employees;
    FirebaseFirestore mFirestore;

    public ManageAssociatesRecyclerViewAdapter(ArrayList<Employee> employees,
                                               FirebaseFirestore mFirestore) {
        this.mFirestore = mFirestore;
        this.employees = employees;
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
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    static class ManageAssociatesRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId;
        TextView tvUsername;
        TextView tvFullName;

        public ManageAssociatesRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = (TextView) itemView.findViewById(R.id.tv_associate_name);
            tvUserId = (TextView) itemView.findViewById(R.id.tv_associate_user_id);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_associate_username);
        }
    }
}
