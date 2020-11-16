package crysalis.example.yssa.ui.managementconsole.messenger;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chatroom_table")
public class Chatroom {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "roomId")
    private Integer mRoomId;
    @NonNull
    @ColumnInfo(name = "recipients")
    private String mRecipients;
    @NonNull
    @ColumnInfo(name = "messages")
    private String mMessages;

    //mRecipients and mMessages will need to be converted from ArrayList to JSONformat then into a
    //string when going into Room and vice versa when being queried from Room
    public Chatroom(@NonNull Integer mRoomId, @NonNull String mRecipients,
                    @NonNull String mMessages) {
        this.mRoomId = mRoomId;
        this.mRecipients = mRecipients;
        this.mMessages = mMessages;
    }

    @NonNull
    public Integer getRoomId() {
        return mRoomId;
    }

    @NonNull
    public String getRecipients() {
        return mRecipients;
    }

    @NonNull
    public String getMessages() {
        return mMessages;
    }
}
