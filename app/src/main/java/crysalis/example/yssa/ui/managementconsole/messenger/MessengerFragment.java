package crysalis.example.yssa.ui.managementconsole.messenger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentMessengerBinding;

public class MessengerFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "Messenger Fragment";
    FirebaseFirestore mFirestore;
    NavController navController;
    ArrayList<String> chatroomIds;
    ImageButton imgBtnCompose;
    RecyclerView rvChatrooms;

    public MessengerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        chatroomIds = new ArrayList<>();
        mFirestore = FirebaseFirestore.getInstance();
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_messenger, container,
                false);
        FragmentMessengerBinding binding = FragmentMessengerBinding.bind(v);
        rvChatrooms = binding.rvChatrooms;
        rvChatrooms.setLayoutManager(new LinearLayoutManager(getContext()));
        rvChatrooms.setAdapter(new MessengerRecyclerViewAdapter(new ArrayList<>()));
        imgBtnCompose = binding.imgBtnCompose;
        imgBtnCompose.setOnClickListener(this);
        navController = Navigation.findNavController(getActivity(), R.id.management_host_fragment);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.img_btn_compose:
                navController.navigate(R.id.chatroom);
                break;
            case R.id.img_btn_menu_messages_main:
                break;
        }
    }
}