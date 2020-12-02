package crysalis.example.yssa.ui.managementconsole.messenger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Distribution;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import crysalis.example.yssa.R;
import crysalis.example.yssa.databinding.FragmentMessengerBinding;
import crysalis.example.yssa.ui.MainActivity;
import crysalis.example.yssa.ui.associateconsole.AssociateConsoleActivity;
import crysalis.example.yssa.ui.login.LoginActivity;
import crysalis.example.yssa.ui.managementconsole.ManagementConsoleActivity;

public class MessengerFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "Messenger Fragment";
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    NavController navController;
    ChatroomRepository repository;
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
//        repository = new ChatroomRepository(getActivity().getApplication());
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