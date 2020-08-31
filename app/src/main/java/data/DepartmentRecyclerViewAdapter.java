package data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;
import crysalis.example.yssa.ui.ChooseAssignmentFragment;


public class DepartmentRecyclerViewAdapter extends RecyclerView.Adapter<DepartmentRecyclerViewAdapter.DepartmentViewHolder> {

    static final int[] departments = {R.drawable.grocery, R.drawable.produce,
            R.drawable.dairy, R.drawable.meat};
    static final String TAG = "Choose Equipment Fragment";
    Context context;
    FragmentManager fm;

    public DepartmentRecyclerViewAdapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DepartmentViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.department_item_view, parent, false), fm);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        holder.departmentImage.setImageResource(departments[position]);
        holder.departmentImage.setAdjustViewBounds(true);

        holder.departmentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        //need arraylist of parts
                        .replace(R.id.fragments_container, new ChooseAssignmentFragment())
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return departments.length;
    }

    static class DepartmentViewHolder extends RecyclerView.ViewHolder {

        ImageView departmentImage;
        public DepartmentViewHolder(@NonNull View itemView, final FragmentManager fm) {
            super(itemView);
            departmentImage = itemView.findViewById(R.id.iv_department_image);
        }
    }
}
