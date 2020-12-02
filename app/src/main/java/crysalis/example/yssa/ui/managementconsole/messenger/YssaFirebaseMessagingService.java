package crysalis.example.yssa.ui.managementconsole.messenger;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class YssaFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMessagingService";

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token);
    }


    public void sendRegistrationToServer(String token) {

    }

}
