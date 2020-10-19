package crysalis.example.yssa.ui.console.schematic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SchematicViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SchematicViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}