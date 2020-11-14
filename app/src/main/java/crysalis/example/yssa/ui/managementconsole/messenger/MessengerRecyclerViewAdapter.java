package crysalis.example.yssa.ui.managementconsole.messenger;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import crysalis.example.yssa.R;

public class MessengerRecyclerViewAdapter extends
        RecyclerView.Adapter<MessengerRecyclerViewAdapter.MessengerRecyclerViewHolder> {

    ArrayList<Message> messages;

    public MessengerRecyclerViewAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessengerRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_messages, parent);
        return new MessengerRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessengerRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessengerRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView timeRecieved;
        TextView sender;
        TextView messagePreview;
        CheckBox checkbox;

        public MessengerRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.tv_sender);
            timeRecieved = itemView.findViewById(R.id.tv_time_recieved);
            messagePreview = itemView.findViewById(R.id.tv_message_preview);
            checkbox = itemView.findViewById(R.id.checkbox_messages);
        }
    }
}
