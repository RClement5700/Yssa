package crysalis.example.yssa.ui.managementconsole.manageassociates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;

public class ManageAssociatesRecyclerViewAdapter extends
        RecyclerView.Adapter<ManageAssociatesRecyclerViewAdapter.ManageAssociatesRecyclerViewHolder> {

    @NonNull
    @Override
    public ManageAssociatesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_manage_associates, parent, false);
        return new ManageAssociatesRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageAssociatesRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ManageAssociatesRecyclerViewHolder extends RecyclerView.ViewHolder {

        public ManageAssociatesRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
