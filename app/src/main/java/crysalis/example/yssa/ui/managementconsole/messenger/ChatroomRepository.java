package crysalis.example.yssa.ui.managementconsole.messenger;


/*
A Repository manages queries and allows you to use multiple backends. In the most common example,
the Repository implements the logic for deciding whether to fetch data from a network or use
results cached in a local database.
 */

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChatroomRepository {

    static ChatroomRepository INSTANCE;
    FirebaseFirestore mFirestore;
    private ChatroomDao mChatroomDao;
    private LiveData<List<Chatroom>> mChatrooms;
    private final static String TAG = "Chatroom Repository";
    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
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

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(final Chatroom chatroom) {
        ChatroomDatabase.databaseWriteExecutor.execute(() -> {
            mChatroomDao.insert(chatroom);
        });
    }
}