package crysalis.example.yssa.ui.console.manageassociates;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManageAssociatesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ManageAssociatesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}