package data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import crysalis.example.yssa.R;

public class InspectionSheetRecyclerViewAdapter extends
        RecyclerView.Adapter<InspectionSheetRecyclerViewAdapter.InspectionSheetRecyclerViewHolder> {
    String[] listOfParts;
    Context context;
    Boolean checkAll = null;
    static final String TAG = "Homescreen Fragment";
    static ArrayList<CheckBox> checkBoxes = new ArrayList<>();


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
        if (checkAll != null) {
            holder.checkBox.setChecked(checkAll);
        }
    }

    @Override
    public int getItemCount() {
        return listOfParts.length;
    }

    public void setAllCheckBoxes(boolean checkAll) {
        this.checkAll = checkAll;
        notifyDataSetChanged();
//        this.checkAll = null;
    }

    static class InspectionSheetRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView partDescription;
        CheckBox checkBox;

        public InspectionSheetRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            partDescription = itemView.findViewById(R.id.tv_part_description);
            checkBox = itemView.findViewById(R.id.checkbox_part);
            checkBoxes.add(checkBox);

        }
    }
}
