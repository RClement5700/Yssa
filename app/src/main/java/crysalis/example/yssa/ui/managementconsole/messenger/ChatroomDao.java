package crysalis.example.yssa.ui.managementconsole.messenger;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChatroomDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Chatroom chatroom);

    @Query("DELETE FROM chatroom_table")
    void deleteAll();

    @Delete
    void delete(Chatroom chatroom);

    @Query("SELECT * FROM chatroom_table ORDER BY roomId ASC")
    LiveData<List<Chatroom>> getChatroomsLiveData();

    @Query("SELECT * FROM chatroom_table ORDER BY roomId ASC")
    List<Chatroom> getChatroomsList();
}