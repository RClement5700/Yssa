package adapters;

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
    static ArrayList<CheckBox> checkBoxes = new ArrayList<>();


    public InspectionSheetRecyclerViewAdapter(String[] listOfParts) {
        this.listOfParts = listOfParts;
    }

    @NonNull
    @Override
    public InspectionSheetRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InspectionSheetRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_inspection, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionSheetRecyclerViewHolder holder, int position) {
        holder.partDescription.setText(listOfParts[position]);
    }

    @Override
    public int getItemCount() {
        return listOfParts.length;
    }

    public boolean allBoxesChecked() {
        for (CheckBox checkBox: checkBoxes) {
            if (!checkBox.isChecked()) return false;
        }
        return true;
    }
    public void setAllCheckBoxes(boolean b) {
        notifyDataSetChanged();
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
