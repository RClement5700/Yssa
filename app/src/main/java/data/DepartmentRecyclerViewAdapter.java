package data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;

public class DepartmentRecyclerViewAdapter extends RecyclerView.Adapter<DepartmentRecyclerViewAdapter.DepartmentViewHolder> {

    static final String[] departmentsTitles = {"GROCERY", "PRODUCE", "DAIRY", "MEAT"};
    static final int[] departments = {R.drawable.grocery, R.drawable.produce,
            R.drawable.dairy, R.drawable.meat};
    Context context;

    public DepartmentRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DepartmentViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.department_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        holder.departmentImage.setImageResource(departments[position]);
        holder.departmentImage.setAdjustViewBounds(true);
        holder.departmentTitle.setText(departmentsTitles[position]);

    }

    @Override
    public int getItemCount() {
        return departments.length;
    }

    class DepartmentViewHolder extends RecyclerView.ViewHolder {

        ImageView departmentImage;
        TextView departmentTitle;
        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            departmentImage = itemView.findViewById(R.id.iv_department_image);
            departmentTitle = itemView.findViewById(R.id.tv_department_title);
        }
    }
}
