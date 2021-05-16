package ro.unibuc.votingapp.presentation;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.auth.FirebaseAuth;

public final class VotingAppViewModel extends AndroidViewModel {
    private static FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

    public VotingAppViewModel(Application application ) {//AndroidViewModel extinde ViewModel si ne permite sa avem acest prototip in constructor
        super( application );
        //Todo
    }

    public static void setFirebaseAuth( FirebaseAuth firebaseAuth ) {
        mFirebaseAuth = firebaseAuth;
    }

    public static FirebaseAuth getFirebaseAuth() {
        return mFirebaseAuth;
    }

    public static String getUserName() {
        return mFirebaseAuth.getCurrentUser().getEmail();
    }

}
