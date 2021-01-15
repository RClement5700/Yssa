package pojos;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;

import java.util.Locale;

public class VoiceProfile {

    //collection: FirebaseFirestore/voiceProfiles
    private static VoiceProfile voiceProfile;
    private String userId; //documentId

    public static VoiceProfile getInstance() {
        if (voiceProfile == null) {
            voiceProfile = new VoiceProfile();
        }
        return voiceProfile;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public void createVoiceProfile() {
        //startRecording()
        //retrieve input from recorder
        //create new Map<String:key, String:value> to set new field in the document
        //
    }
}
