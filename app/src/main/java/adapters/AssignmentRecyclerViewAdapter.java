package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;
import pojos.Assignment;

public class AssignmentRecyclerViewAdapter extends
        RecyclerView.Adapter<AssignmentRecyclerViewAdapter.AssignmentRecyclerViewHolder> {
    Assignment assignment;
    Context context;

    public AssignmentRecyclerViewAdapter(Assignment assignment, Context context) {
        this.assignment = assignment;
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

        final ViewGroup container = holder.itemView.findViewById(R.id.user_input_container);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                    TODO:
                        -inflate view that prompts for checkdigit
                        -ask for quantity after correct checkdigit is input
                        -make arraylist for go-backs
                        -if quantity is less than expected:
                            -ask for quantity being input
                            -goBacksArrayList.add(quantity - input)
                 */
                LayoutInflater.from(context).inflate(R.layout.fragment_user_input,
                        container,
                        false);
            }
        });
    }

    @Override
    public int getItemCount() {
//        return order.getProducts().size();
        return 0;
    }

    static class AssignmentRecyclerViewHolder extends RecyclerView.ViewHolder {

        public AssignmentRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}