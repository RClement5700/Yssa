package pojos;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Message {
    String time;
    String sender;
    String messageBody;
    String profilePictureUrl;

    public Message(String time, String sender, String messageBody, String profilePictureUrl) {
        this.time = time;
        this.sender = sender;
        this.messageBody = messageBody;
        this.profilePictureUrl = profilePictureUrl;
    }
}
