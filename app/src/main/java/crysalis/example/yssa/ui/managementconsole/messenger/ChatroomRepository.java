package crysalis.example.yssa.ui.managementconsole.messenger;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;

public class ChatroomRepository {

    static ChatroomRepository INSTANCE;
    private ChatroomDao mChatroomDao;
    private LiveData<List<Chatroom>> mChatrooms;
    private final static String TAG = "Chatroom Repository";
    FirebaseFirestore mFirestore;


    private ChatroomRepository(Application application) {
        ChatroomDatabase db = ChatroomDatabase.getDatabase(application);
        mFirestore = FirebaseFirestore.getInstance();
        mChatroomDao = db.chatroomDao();
        mChatrooms = mChatroomDao.getChatroomsLiveData();
    }

    public static ChatroomRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new ChatroomRepository(application);
        }
        return INSTANCE;
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Chatroom>> getChatrooms() {
        return mChatrooms;
    }

    // You must call the following methods: insert(), remove(); on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final Chatroom chatroom) {
        ChatroomDatabase.databaseWriteExecutor.execute(() -> {
            mChatroomDao.insert(chatroom);
        });
    }

    void remove(final Chatroom chatroom) {
        ChatroomDatabase.databaseWriteExecutor.execute(() -> {
            mChatroomDao.delete(chatroom);
        });
    }
}