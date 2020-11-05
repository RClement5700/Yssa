package crysalis.example.yssa.ui.associateconsole.chooserole;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;


public class RoleRecyclerViewAdapter extends
        RecyclerView.Adapter<RoleRecyclerViewAdapter.RoleViewHolder> {
    Context context;
    String[] roleTitles;
    int[] roleImages = {R.drawable.order, R.drawable.forklift, R.drawable.loader, R.drawable.training};

    public RoleRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        roleTitles = context.getResources().getStringArray(R.array.role_titles);
        return new RoleViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_view_role, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        holder.tvRoleTitle.setText(roleTitles[position]);
        holder.ivRoleImage.setImageDrawable(context.getDrawable(roleImages[position]));
    }

    @Override
    public int getItemCount() {
        return roleImages.length;
    }

    static class RoleViewHolder extends RecyclerView.ViewHolder {
        TextView tvRoleTitle;
        ImageView ivRoleImage;
        public RoleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRoleTitle = itemView.findViewById(R.id.tv_role_title);
            ivRoleImage = itemView.findViewById(R.id.iv_role_image);
        }
    }
}
