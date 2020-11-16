package crysalis.example.yssa.ui.managementconsole.messenger;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import pojos.Message;

@Entity(tableName = "chatroom_table")
public class Chatroom {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "roomId")
    private Integer mRoomId;
    @NonNull
    @ColumnInfo(name = "recipients")
    private ArrayList<String> mRecipients;
    @NonNull
    @ColumnInfo(name = "messages")
    private ArrayList<Message> mMessages;

    public Chatroom(@NonNull Integer mRoomId, @NonNull ArrayList<String> mRecipients,
                    @NonNull ArrayList<Message> mMessages) {
        this.mRoomId = mRoomId;
        this.mRecipients = mRecipients;
        this.mMessages = mMessages;
    }

    public int getmRoomId() {
        return mRoomId;
    }

    public ArrayList<String> getmRecipients() {
        return mRecipients;
    }

    public ArrayList<Message> getmMessages() {
        return mMessages;
    }
}
