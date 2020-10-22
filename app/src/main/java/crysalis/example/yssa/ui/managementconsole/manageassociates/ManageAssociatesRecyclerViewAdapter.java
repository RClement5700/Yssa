package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Employee;

public class ManageAssociatesRecyclerViewAdapter extends
        RecyclerView.Adapter<ManageAssociatesRecyclerViewAdapter.ManageAssociatesRecyclerViewHolder> {
    ArrayList<Employee> employees;

    public ManageAssociatesRecyclerViewAdapter(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public ManageAssociatesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_manage_associates, parent, false);
        return new ManageAssociatesRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageAssociatesRecyclerViewHolder holder, int position) {
        holder.tvUsername.setText(employees.get(position).getUsername());
        holder.tvUserId.setText(employees.get(position).getEmployeeId());
        holder.tvFullName.setText(employees.get(position).getFullName());
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
