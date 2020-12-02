package crysalis.example.yssa.ui.managementconsole.messenger;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ChatroomViewModel extends AndroidViewModel {

    private ChatroomRepository mRepository;

    private final LiveData<List<Chatroom>> mChatrooms;

    public ChatroomViewModel (Application application) {
        super(application);
        mRepository = ChatroomRepository.getInstance(application);
        mChatrooms = mRepository.getChatrooms();
    }

    LiveData<List<Chatroom>> getChatroom() { return mChatrooms; }

    public void insert(Chatroom chatroom) { mRepository.insert(chatroom); }
}