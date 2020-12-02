package crysalis.example.yssa.ui.managementconsole.messenger;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Employee;

public class DisplayRecipientsRecyclerViewAdapter extends
        RecyclerView.Adapter<DisplayRecipientsRecyclerViewAdapter.DisplayRecipientsViewHolder> {
    ArrayList<Employee> employees;
    Context context;

    public DisplayRecipientsRecyclerViewAdapter(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
    }

    @NonNull
    @Override
    public DisplayRecipientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_view_display_recipient, parent);
        return new DisplayRecipientsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayRecipientsViewHolder holder, int position) {
        holder.tvAssociateName.setText(employees.get(position).getFullName());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class DisplayRecipientsViewHolder extends RecyclerView.ViewHolder {
        TextView tvAssociateName;
        ImageView ivProfilePicture;

        public DisplayRecipientsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAssociateName = itemView.findViewById(R.id.tv_associate_name);
            ivProfilePicture = itemView.findViewById(R.id.iv_profile_picture);
        }
    }
}
