package crysalis.example.yssa.ui.managementconsole.messenger;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/* -we set exportSchema to false here to avoid a build warning. In a real app, you should consider
    setting a directory for Room to use to export the schema so you can check the current schema into
    your version control system.

    -When you modify the database schema, you'll need to update the version number and define a
    migration strategy https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
*/
@Database(entities = {Chatroom.class}, version = 1, exportSchema = false)
public abstract class ChatroomDatabase extends RoomDatabase {

    public abstract ChatroomDao chatroomDao();

    private static volatile ChatroomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ChatroomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ChatroomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ChatroomDatabase.class, "chatroom_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}