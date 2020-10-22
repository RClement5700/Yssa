package adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import pojos.Order;

public class OrderRecyclerViewAdapter extends
        RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderRecyclerViewHolder> {
    ArrayList<Order> orders;

    public OrderRecyclerViewAdapter(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(parent.getContext(), R.layout.item_view_order, parent);
        return new OrderRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerViewHolder holder, int position) {
        String orderId = String.valueOf(orders.get(position).getOrderNumber());
        String userId = String.valueOf(orders.get(position).getUserId());
        holder.tvOrderId.append(orderId);
        holder.tvOrderId.append(userId);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrderId;
        TextView tvUserId;
        public OrderRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderId = (TextView) itemView.findViewById(R.id.order_id);
            tvUserId = (TextView) itemView.findViewById(R.id.user_id);
        }
    }
}
