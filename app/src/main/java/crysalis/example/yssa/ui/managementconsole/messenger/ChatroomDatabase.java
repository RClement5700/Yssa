package crysalis.example.yssa.ui.managementconsole.messenger;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pojos.Message;


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

    static ChatroomDatabase getDatabase(final Context context) {
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

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        FirebaseFirestore mFirestore;
        private final static String TAG = "RoomDatabaseCallback";
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            mFirestore = FirebaseFirestore.getInstance();
            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ChatroomDao dao = INSTANCE.chatroomDao();
                dao.deleteAll();
                mFirestore.collection("chats")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                Log.d(TAG, "Retrieving Chatrooms Task Complete ", task.getException());
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Num of Chatrroom Docs: " +
                                            task.getResult().getDocuments().size());
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Long chatroomId = (Long) document.get("chatroomId");
                                        ArrayList<String> recipients = (ArrayList<String>) document.get("recipients");
                                        ArrayList<Message> messages = (ArrayList<Message>) document.get("messages");
                                        Chatroom chatroom = new Chatroom(chatroomId.intValue(), recipients, messages);
                                        dao.insert(chatroom);
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
            });
        }
    };
}