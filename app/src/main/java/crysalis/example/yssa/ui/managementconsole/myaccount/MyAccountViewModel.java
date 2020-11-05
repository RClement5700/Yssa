package crysalis.example.yssa.ui.managementconsole.myaccount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyAccountViewModel extends ViewModel {

    private MutableLiveData<String> mEmail, mDisplayName, mImageUrl;

    public MyAccountViewModel() {
        mEmail = new MutableLiveData<>();
        mDisplayName = new MutableLiveData<>();
        mImageUrl = new MutableLiveData<>();
    }

    public LiveData<String> getEmail() {
        return mEmail;
    }
    public LiveData<String> getDisplayName() {
        return mDisplayName;
    }
    public LiveData<String> getImageUrl() {
        return mImageUrl;
    }

}