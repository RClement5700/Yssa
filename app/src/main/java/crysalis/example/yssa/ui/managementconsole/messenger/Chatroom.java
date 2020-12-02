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
    private String mRoomId;
//    @NonNull
//    @ColumnInfo(name = "messages")
//    private String mMessages;

    //mReceivers and mMessages will need to be converted from ArrayList to JSONformat then into a
    //string when going into Room and vice versa when being queried from Room
    public Chatroom(@NonNull String mRoomId
                    /*, @NonNull String mMessages*/) {
        this.mRoomId = mRoomId;
//        this.mMessages = mMessages;
    }

    @NonNull
    public String getRoomId() {
        return mRoomId;
    }

//    @NonNull
//    public String getMessages() {
//        return mMessages;
//    }
}
