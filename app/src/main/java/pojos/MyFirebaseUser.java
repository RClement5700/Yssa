package pojos;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.UserInfo;
import java.util.List;

public class MyFirebaseUser extends FirebaseUser implements Parcelable {

    private Creator CREATOR;
    private FirebaseUser firebaseUser;
    private String fullName;
    private String displayName;
    private String imgPath;
    private int employeeId;

    public MyFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }

    public MyFirebaseUser(FirebaseUser firebaseUser, String fullName, String displayName, int employeeId) {
        this.firebaseUser = firebaseUser;
        this.fullName = fullName;
        this.displayName = displayName;
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @NonNull
    @Override
    public String getUid() {
        return firebaseUser.getUid();
    }

    @NonNull
    @Override
    public String getProviderId() {
        return firebaseUser.getProviderId();
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Nullable
    @Override
    public List<String> zza() {
        return firebaseUser.zza();
    }

    @NonNull
    @Override
    public List<? extends UserInfo> getProviderData() {
        return firebaseUser.getProviderData();
    }

    @NonNull
    @Override
    public FirebaseUser zza(@NonNull List<? extends UserInfo> list) {
        return firebaseUser.zza(firebaseUser.getProviderData());
    }

    @Override
    public FirebaseUser zzb() {
        return null;
    }

    @NonNull
    @Override
    public FirebaseApp zzc() {
        return firebaseUser.zzc();
    }

    @Nullable
    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Nullable
    @Override
    public Uri getPhotoUrl() {
        return null;
    }

    @Nullable
    @Override
    public String getEmail() {
        return firebaseUser.getEmail();
    }

    @Nullable
    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public boolean isEmailVerified() {
        return false;
    }

    @Nullable
    @Override
    public String getTenantId() {
        return null;
    }

    @NonNull
    @Override
    public zzff zzd() {
        return firebaseUser.zzd();
    }

    @Override
    public void zza(@NonNull zzff zzff) {

    }

    @NonNull
    @Override
    public String zze() {
        return firebaseUser.zze();
    }

    @NonNull
    @Override
    public String zzf() {
        return firebaseUser.zzf();
    }

    @Nullable
    @Override
    public FirebaseUserMetadata getMetadata() {
        return null;
    }

    @NonNull
    @Override
    public MultiFactor getMultiFactor() {
        return firebaseUser.getMultiFactor();
    }

    @Override
    public void zzb(List<MultiFactorInfo> list) {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
