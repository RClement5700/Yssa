package data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;
import crysalis.example.yssa.ui.ChooseEquipmentFragment;


/*
    TODO: why is the last image in the recyclerview being cropped?
 */
public class DepartmentRecyclerViewAdapter extends RecyclerView.Adapter<DepartmentRecyclerViewAdapter.DepartmentViewHolder> {

    static String[] departmentsTitles;
    static final int[] departments = {R.drawable.grocery, R.drawable.produce,
            R.drawable.dairy, R.drawable.meat};
    static final String TAG = "Choose Equipment Fragment";
    Context context;
    FragmentManager fm;

    public DepartmentRecyclerViewAdapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
        departmentsTitles = context.getResources().getStringArray(R.array.department_titles);
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
        holder.departmentTitle.setText(departmentsTitles[position]);
        holder.departmentImage.setAdjustViewBounds(true);

        holder.departmentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.fragments_container, new ChooseEquipmentFragment())
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
        TextView departmentTitle;
        public DepartmentViewHolder(@NonNull View itemView, final FragmentManager fm) {
            super(itemView);
            departmentImage = itemView.findViewById(R.id.iv_department_image);
            departmentTitle = itemView.findViewById(R.id.tv_department_title);
        }
    }
}
