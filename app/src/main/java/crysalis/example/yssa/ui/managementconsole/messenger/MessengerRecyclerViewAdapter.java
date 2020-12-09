package crysalis.example.yssa.ui.managementconsole.messenger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import crysalis.example.yssa.R;

public class MessengerRecyclerViewAdapter extends
        RecyclerView.Adapter<MessengerRecyclerViewAdapter.MessengerRecyclerViewHolder> {

    List<String> chatroomIds;

    public MessengerRecyclerViewAdapter(List<String> chatroomIds) {
        this.chatroomIds = chatroomIds;
    }

    @NonNull
    @Override
    public MessengerRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_chatrooms, parent,
                false);
        return new MessengerRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessengerRecyclerViewHolder holder, int position) {
        holder.sender.setText(chatroomIds.get(position));
    }

    @Override
    public int getItemCount() {
        return chatroomIds.size();
    }


    public void setData(List<Chatroom> newData) {
        List<String> Ids = new ArrayList<String>();
        for (Chatroom chatroom: newData) {
            Ids.add(chatroom.getRoomId());
        }
        this.chatroomIds = Ids;
        notifyDataSetChanged();
    }


    static class MessengerRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView timeRecieved;
        TextView sender;
        TextView messagePreview;
        ImageView ivProfilePicture;
        CheckBox checkbox;

        public MessengerRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.tv_sender);
            timeRecieved = itemView.findViewById(R.id.tv_time_recieved);
            messagePreview = itemView.findViewById(R.id.tv_message_preview);
            ivProfilePicture = itemView.findViewById(R.id.iv_profile_picture);
            checkbox = itemView.findViewById(R.id.checkbox_messages);
        }
    }
}
