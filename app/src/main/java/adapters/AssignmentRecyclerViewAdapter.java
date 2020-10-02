package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Assignment;

public class AssignmentRecyclerViewAdapter extends
        RecyclerView.Adapter<AssignmentRecyclerViewAdapter.AssignmentRecyclerViewHolder> {
    ArrayList<Assignment> assignments;
    Context context;

    //this will be a list of Assignments
    //will be blank if no assignments have been assigned for the day
    //completed assignments will be listed beneath the current assignment
    //clicking an assignment will open a list of Products to be picked
    public AssignmentRecyclerViewAdapter(ArrayList<Assignment> assignments, Context context) {
        this.assignments = assignments;
        this.context = context;
    }

    public AssignmentRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AssignmentRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_view_assignment, parent, false);
        return new AssignmentRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentRecyclerViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    static class AssignmentRecyclerViewHolder extends RecyclerView.ViewHolder {

        public AssignmentRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}