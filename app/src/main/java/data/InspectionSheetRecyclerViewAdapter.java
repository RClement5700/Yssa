package data;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.InspectionItemViewBinding;

public class InspectionSheetRecyclerViewAdapter extends
        RecyclerView.Adapter<InspectionSheetRecyclerViewAdapter.InspectionSheetRecyclerViewHolder> {

    //list will differ between forklift and epj
    String[] listOfParts;
    Context context;

    public InspectionSheetRecyclerViewAdapter(String[] listOfParts, Context context) {
        this.listOfParts = listOfParts;
        this.context = context;
    }

    @NonNull
    @Override
    public InspectionSheetRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InspectionSheetRecyclerViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.inspection_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionSheetRecyclerViewHolder holder, int position) {
        holder.partDescription.setText(listOfParts[position]);
    }

    @Override
    public int getItemCount() {
        return listOfParts.length;
    }

    static class InspectionSheetRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView partDescription;

        public InspectionSheetRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            InspectionItemViewBinding binding
                    = InspectionItemViewBinding.inflate(LayoutInflater.from(itemView.getContext()));
            View v = binding.getRoot();
            partDescription = v.findViewById(R.id.tv_part_description);
        }
    }
}
