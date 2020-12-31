package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;
import java.util.zip.Inflater;

import crysalis.example.yssa.R;
import crysalis.example.yssa.ui.orderpicking.CurrentOrderFragment;
import pojos.Product;

public class OrderItemsRecyclerViewAdapter extends RecyclerView.Adapter<OrderItemsRecyclerViewAdapter.OrderItemViewHolder> {

    ArrayList<CurrentOrderFragment.OrderItem> orderItems;

    public OrderItemsRecyclerViewAdapter(ArrayList<CurrentOrderFragment.OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_order_item, parent, false);
        return new OrderItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        CurrentOrderFragment.OrderItem orderItem = orderItems.get(position);
        holder.tvSlotId.setText(String.valueOf(position));
        holder.tvProductDescription.setText(orderItems.get(position).getProduct().getDescription());
        holder.tvPickQuantity.setText(String.valueOf(orderItems.get(position).getPickQuantity()));
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    static class OrderItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvSlotId, tvProductDescription, tvPickQuantity;
        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSlotId = (TextView) itemView.findViewById(R.id.tv_slotId);;
            tvProductDescription = (TextView) itemView.findViewById(R.id.tv_product_description);;
            tvPickQuantity = (TextView) itemView.findViewById(R.id.tv_pick_quantity);

        }
    }

}
