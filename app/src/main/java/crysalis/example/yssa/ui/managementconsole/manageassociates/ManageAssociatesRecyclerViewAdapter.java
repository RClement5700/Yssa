package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import crysalis.example.yssa.R;

public class ManageAssociatesRecyclerViewAdapter extends
        RecyclerView.Adapter<ManageAssociatesRecyclerViewAdapter.ManageAssociatesRecyclerViewHolder> {

    ArrayList<FirebaseUser> firebaseUsers;

    public ManageAssociatesRecyclerViewAdapter(ArrayList<FirebaseUser> firebaseUsers) {
        this.firebaseUsers = firebaseUsers;
    }

    @Override
    public ManageAssociatesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_manage_associates,
                parent, false);
        return new ManageAssociatesRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ManageAssociatesRecyclerViewHolder holder, int position) {
        holder.tvUserId.setText(firebaseUsers.get(position).getUid());
        holder.tvUsername.setText(firebaseUsers.get(position).getDisplayName());
        holder.tvFullName.setText(firebaseUsers.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return firebaseUsers.size();
    }

    static class ManageAssociatesRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId;
        TextView tvUsername;
        TextView tvFullName;
        ManageAssociatesItemViewModel itemViewModel;

        public ManageAssociatesRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = (TextView) itemView.findViewById(R.id.tv_associate_name);
            tvUserId = (TextView) itemView.findViewById(R.id.tv_associate_user_id);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_associate_username);
        }
    }
}
