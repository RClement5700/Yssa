package adapters;

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
import crysalis.example.yssa.ui.InspectEquipmentFragment;
import crysalis.example.yssa.ui.TrainerFragment;


public class AssignmentRecyclerViewAdapter extends
        RecyclerView.Adapter<AssignmentRecyclerViewAdapter.AssignmentViewHolder> {

    static final int[] assignments = {R.drawable.trainer, R.drawable.orderpicker,
            R.drawable.replenish, R.drawable.hooks, R.drawable.loader};
//    static final int[] assignments = {R.drawable.architecture, R.drawable.cyberconnectivity,
//        R.drawable.lightspeed, R.drawable.merica, R.drawable.androidwallpaper};
//    static final int[] assignmentTitles = {R.array.assignment_titles};
    String[] checklist = null;
    Context context;
    FragmentManager fm;

    public AssignmentRecyclerViewAdapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AssignmentViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.assignment_item_view, parent, false), fm);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        holder.assignmentImage.setImageResource(assignments[position]);
        holder.assignmentImage.setAdjustViewBounds(true);
        if (position == 1 || position == 3 || position == 4) {
            checklist = context.getResources().getStringArray(R.array.electric_pallet_jack_inspection_data_list);
        }
        else if (position == 2) {
            checklist = context.getResources().getStringArray(R.array.forklift_inspection_data_list);
        }
        holder.assignmentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checklist != null) {
                    fm.beginTransaction()
                            .replace(R.id.fragments_container, new InspectEquipmentFragment(checklist))
                            .addToBackStack(null)
                            .commit();
                }
                else {
                    fm.beginTransaction()
                            .replace(R.id.fragments_container, new TrainerFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignments.length;
    }

    static class AssignmentViewHolder extends RecyclerView.ViewHolder {

        ImageView assignmentImage;
        TextView assignmentTitle;
        public AssignmentViewHolder(@NonNull View itemView, final FragmentManager fm) {
            super(itemView);
            assignmentImage = itemView.findViewById(R.id.iv_assignment_image);
        }
    }
}
