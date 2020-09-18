package services;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;

public class CrysalisFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }
}
