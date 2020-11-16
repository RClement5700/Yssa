package crysalis.example.yssa.ui.managementconsole.messenger;


/*
A Repository manages queries and allows you to use multiple backends. In the most common example,
the Repository implements the logic for deciding whether to fetch data from a network or use
results cached in a local database.
 */

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class ChatroomRepository {

    private ChatroomDao mChatroomDao;
    private LiveData<List<Chatroom>> mChatrooms;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ChatroomRepository(Application application) {
        ChatroomDatabase db = ChatroomDatabase.getDatabase(application);
        mChatroomDao = db.chatroomDao();
        mChatrooms = mChatroomDao.getChatrooms();
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