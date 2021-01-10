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
import crysalis.example.yssa.ui.associateconsole.InspectEquipmentFragment;

public class InspectionSheetRecyclerViewAdapter extends
        RecyclerView.Adapter<InspectionSheetRecyclerViewAdapter.InspectionSheetRecyclerViewHolder> {
    static String[] listOfParts;
    ArrayList<InspectEquipmentFragment.Obj> objList;

    public InspectionSheetRecyclerViewAdapter(Context context, ArrayList<InspectEquipmentFragment.Obj> objList) {
        listOfParts =
                context.getResources().getStringArray(R.array.forklift_inspection_data_list);
        this.objList = objList;
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
        final InspectEquipmentFragment.Obj obj = objList.get(position);
        holder.partDescription.setText(listOfParts[position]);
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(obj.isSelected());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                obj.setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfParts.length;
    }

    public boolean allBoxesChecked() {
        for (InspectEquipmentFragment.Obj obj : objList) {
            if (!obj.isSelected()) return false;
        }
        return true;
    }

    static class InspectionSheetRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView partDescription;
        CheckBox checkBox;
        boolean isChecked;
        public InspectionSheetRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            partDescription = itemView.findViewById(R.id.tv_part_description);
            checkBox = itemView.findViewById(R.id.checkbox_part);
            isChecked = false;
        }
    }
}
