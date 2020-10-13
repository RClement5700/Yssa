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
import crysalis.example.yssa.ui.associateconsole.InspectEquipmentFragment;
import crysalis.example.yssa.ui.associateconsole.TrainerFragment;


public class DesignationRecyclerViewAdapter extends
        RecyclerView.Adapter<DesignationRecyclerViewAdapter.DesignationViewHolder> {

    static final int[] designations = {R.drawable.trainer, R.drawable.orderpicker,
            R.drawable.replenish, R.drawable.hooks, R.drawable.loader};
//    static final int[] designations = {R.drawable.architecture, R.drawable.cyberconnectivity,
//        R.drawable.lightspeed, R.drawable.merica, R.drawable.androidwallpaper};
//    static final int[] designationTitles = {R.array.designation_titles};
    String[] checklist = null;
    Context context;
    FragmentManager fm;

    public DesignationRecyclerViewAdapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    @NonNull
    @Override
    public DesignationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DesignationViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_view_designation, parent, false), fm);
    }

    @Override
    public void onBindViewHolder(@NonNull DesignationViewHolder holder, int position) {
        holder.designationImage.setImageResource(designations[position]);
        holder.designationImage.setAdjustViewBounds(true);
        if (position == 1 || position == 3 || position == 4) {
            checklist = context.getResources().getStringArray(R.array.electric_pallet_jack_inspection_data_list);
        }
        else if (position == 2) {
            checklist = context.getResources().getStringArray(R.array.forklift_inspection_data_list);
        }
        holder.designationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checklist != null) {
                    fm.beginTransaction()
//                            .replace(R.id.fragments_container, new InspectEquipmentFragment(checklist))
                            .addToBackStack(null)
                            .commit();
                }
                else {
                    fm.beginTransaction()
//                            .replace(R.id.fragments_container, new TrainerFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return designations.length;
    }

    static class DesignationViewHolder extends RecyclerView.ViewHolder {

        ImageView designationImage;
        TextView designationTitle;
        public DesignationViewHolder(@NonNull View itemView, final FragmentManager fm) {
            super(itemView);
            designationImage = itemView.findViewById(R.id.iv_designation_image);
        }
    }
}
