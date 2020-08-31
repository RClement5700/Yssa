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
import crysalis.example.yssa.ui.ChooseAssignmentFragment;
import crysalis.example.yssa.ui.ChooseDepartmentFragment;


public class AssignmentRecyclerViewAdapter extends
        RecyclerView.Adapter<AssignmentRecyclerViewAdapter.AssignmentViewHolder> {

    static final int[] assignments = {R.drawable.trainer, R.drawable.orderpicker,
            R.drawable.replenish, R.drawable.hooks};
    static final String TAG = "Choose Department Fragment";
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

        holder.assignmentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.homescreen_fragment_container, new ChooseDepartmentFragment())
                        .addToBackStack(TAG)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignments.length;
    }

    static class AssignmentViewHolder extends RecyclerView.ViewHolder {

        ImageView assignmentImage;
        public AssignmentViewHolder(@NonNull View itemView, final FragmentManager fm) {
            super(itemView);
            assignmentImage = itemView.findViewById(R.id.iv_assignment_image);
        }
    }
}
