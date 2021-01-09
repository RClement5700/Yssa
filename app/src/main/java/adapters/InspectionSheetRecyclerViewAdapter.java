package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import crysalis.example.yssa.R;

public class InspectionSheetRecyclerViewAdapter extends
        RecyclerView.Adapter<InspectionSheetRecyclerViewAdapter.InspectionSheetRecyclerViewHolder> {
    static String[] listOfParts;
    static ArrayList<Boolean> cc = new ArrayList<>();

    public InspectionSheetRecyclerViewAdapter(Context context) {
        listOfParts =
                context.getResources().getStringArray(R.array.forklift_inspection_data_list);
        for (String s: listOfParts) cc.add(false);
    }

    @NonNull
    @Override
    public InspectionSheetRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_view_inspection, parent, false);
        return new InspectionSheetRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InspectionSheetRecyclerViewHolder holder, int position) {
        holder.partDescription.setText(listOfParts[position]);
        holder.checkBox.setChecked(cc.get(position));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cc.remove(position);
                cc.add(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfParts.length;
    }

//    public boolean allBoxesChecked() {
//        for (CheckBox checkbox: checkBoxes) {
//            if (!checkbox.isChecked()) return false;
//        }
//        return true;
//    }

    class InspectionSheetRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView partDescription;
        CheckBox checkBox;
        public InspectionSheetRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            partDescription = itemView.findViewById(R.id.tv_part_description);
            checkBox = itemView.findViewById(R.id.checkbox_part);
        }
    }
}
