package pojos;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

public class Message {
    String messageBody;
    String receiver;
    String sender;
    Timestamp timestamp;

    public Message(String messageBody, String sender, String receiver, Timestamp timestamp) {
        this.sender = sender;
        this.messageBody = messageBody;
        this.receiver = receiver;
        this.timestamp = timestamp;
    }

    public Message(String messageBody, Timestamp timestamp) {
        sender = "users/8ZEhQnHY4WGeYuuNgY7e";
        receiver = "users/6kR1RUzsund0WAZJSi2I";
        this.messageBody = messageBody;
        this.timestamp = timestamp;
    }

}
