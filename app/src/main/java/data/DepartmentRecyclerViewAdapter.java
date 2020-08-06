package data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

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
    }

    @Override
    public int getItemCount() {
        return departments.length;
    }

    class DepartmentViewHolder extends RecyclerView.ViewHolder {

        ImageView departmentImage;
        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            departmentImage = itemView.findViewById(R.id.iv_department_image);
        }
    }
}
