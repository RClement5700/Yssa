package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import crysalis.example.yssa.R;
import pojos.Order;

public class OrderPickingRecyclerViewAdapter extends
        RecyclerView.Adapter<OrderPickingRecyclerViewAdapter.OrderPickingRecyclerViewHolder> {
    Order order;
    Context context;

    public OrderPickingRecyclerViewAdapter(Order order, Context context) {
        this.order = order;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderPickingRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.order_picking_item_view, parent, false);
        return new OrderPickingRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderPickingRecyclerViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /*
                    TODO:
                        -open alert dialog that prompts for checkdigit
                        -ask for quantity after correct checkdigit is input
                        -make arraylist for go-backs
                        -if quantity is less than expected:
                            -ask for quantity being input
                            -goBacksArrayList.add(quantity - input)
                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return order.getProductCount();
    }

    static class OrderPickingRecyclerViewHolder extends RecyclerView.ViewHolder {

        public OrderPickingRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
