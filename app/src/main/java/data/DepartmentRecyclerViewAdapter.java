package data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import crysalis.example.yssa.R;

public class DepartmentRecyclerViewAdapter extends RecyclerView.Adapter<DepartmentRecyclerViewAdapter.DepartmentViewHolder> {

    static final String[] departmentsTitle = {"GROCERY", "PRODUCE", "DAIRY", "MEAT"};
    static final HashMap<String, Integer> departments = new HashMap<>();
    Context context;

    public DepartmentRecyclerViewAdapter(Context context) {
        this.context = context;
        departments.put("GROCERY", R.drawable.grocery);
        departments.put("PRODUCE", R.drawable.produce);
        departments.put("DAIRY", R.drawable.dairy);
        departments.put("MEAT", R.drawable.meat);
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DepartmentViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.department_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        for (int i = 0; i < getItemCount(); i++) {
        }

    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    class DepartmentViewHolder extends RecyclerView.ViewHolder {

        ImageView departmentImage;
        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            departmentImage = itemView.findViewById(R.id.iv_department_image);
        }
    }
}
