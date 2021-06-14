package ro.unibuc.votingapp.presentation;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.VotAnonim;
import ro.unibuc.votingapp.domain.VoteDependencyProvider;
import ro.unibuc.votingapp.domain.VoteUseCase;

public final class VotingAppViewModel extends AndroidViewModel {
    private static FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    private final VoteUseCase voteUseCase;

    public VotingAppViewModel( Application application ) {//AndroidViewModel extinde ViewModel si ne permite sa avem acest prototip in constructor
        super( application );
        VoteDependencyProvider voteDependencyProvider = new VoteDependencyProvider( application );
        this.voteUseCase = voteDependencyProvider.provideUseCase();      //comunicarea cu domain si data
    }

    public static void setFirebaseAuth( FirebaseAuth firebaseAuth ) {
        mFirebaseAuth = firebaseAuth;
    }

    public static FirebaseAuth getFirebaseAuth() {
        return mFirebaseAuth;
    }

    public static String getUserName() {
        return Objects.requireNonNull( mFirebaseAuth.getCurrentUser() ).getEmail();
    }

    //Functii pentru Binding
    public LiveData < List < Locatie > > getLocatii() {
        return voteUseCase.getLocatii();
    }


    public LiveData < List < Candidat > > getCandidati( String idAlegere ) {
        return voteUseCase.getCandidati( idAlegere );
    }

    public LiveData < List < Alegere > > getAlegeri( String idLocatie ) {
        return voteUseCase.getAlegeri( idLocatie );
    }


    public LiveData < List < Stire > > getStiri( String idAlegere ) {
        return voteUseCase.getStiri( idAlegere );
    }

    public void insertVot( VotAnonim votAnonim ) {
        voteUseCase.insertVot( votAnonim );
    }
}
