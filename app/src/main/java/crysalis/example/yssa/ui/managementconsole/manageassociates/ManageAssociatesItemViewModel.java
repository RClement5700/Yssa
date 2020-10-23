package crysalis.example.yssa.ui.managementconsole.manageassociates;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManageAssociatesItemViewModel extends ViewModel {
//    private MutableLiveData<String> userId;
//    private MutableLiveData<String> username;
//    private MutableLiveData<String> userFullName;

    public ManageAssociatesItemViewModel(MutableLiveData<String> userId,
                                         MutableLiveData<String> username,
                                         MutableLiveData<String> userFullName)
    {
        userId = new MutableLiveData<>();
        userFullName = new MutableLiveData<>();
        username = new MutableLiveData<>();
        userId.setValue("This is gallery fragment");
        userFullName.setValue("This is gallery fragment");
        username.setValue("This is gallery fragment");
    }

//    public MutableLiveData<String> getUserId() {
//        return userId;
//    }
//
//    public MutableLiveData<String> getUsername() {
//        return username;
//    }
//
//    public MutableLiveData<String> getUserFullName() {
//        return userFullName;
//    }
}
