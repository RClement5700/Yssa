package crysalis.example.yssa.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import crysalis.example.yssa.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment implements View.OnClickListener {
    FirebaseFirestore mFirestore;
    EditText etEmployeeId;
    EditText etFirstName;
    EditText etLastName;
    ProgressBar progressBar;
    ImageView imgBtnContinue;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentRegisterBinding binding = FragmentRegisterBinding.inflate(inflater);
        View v = binding.getRoot();
        mFirestore = FirebaseFirestore.getInstance();
        etFirstName = binding.etEnterFirstName;
        etLastName = binding.etEnterLastName;
        progressBar = binding.progressBar;
        imgBtnContinue = binding.ivContinue;
        return v;
    }


    @Override
    public void onClick(View v) {
        register();
        progressBar.setVisibility(View.GONE);
    }

    private void register() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String firstInitial = (" " + firstName.charAt(0)).toUpperCase().trim();
        String lastInitial = (" " + lastName.charAt(0)).toUpperCase().trim();
        String fullName = firstInitial + firstName.substring(1, firstName.length())
                + " " + lastInitial + lastName.substring(1, lastName.length());
        int uid = (int) Math.ceil(new Random().nextInt(10000));
        String UID = String.valueOf(uid);
        String displayName = fullName.toLowerCase().replace(" ", ".") + "." +
                UID;
        System.err.println("Full Name: " + fullName);
        System.err.println("Display Name: " + displayName);
        Map<String, String> user = new HashMap<>();
        user.put("displayName", displayName);
        user.put("fullName", fullName);
        if (!fullName.isEmpty() && !displayName.isEmpty() && !UID.isEmpty()) {
            mFirestore.collection("users").document(UID)
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
        }
    }
}