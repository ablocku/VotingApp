package ro.unibuc.votingapp.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.VotAnonim;


public final class VoteUseCase {
    private final VoteMediator mGameMediator;

    VoteUseCase( VoteMediator mediator ) {
        this.mGameMediator = mediator;
    }

    public LiveData < List < Candidat > > getCandidati( String idAlegere ) {
        return mGameMediator.getCandidati( idAlegere );
    }

    public LiveData < List < Alegere > > getAlegeri( String idLocatie ) {
        return mGameMediator.getAlegeri( idLocatie );
    }

    public LiveData < List < Locatie > > getLocatii() {
        return mGameMediator.getLocatii();
    }

    public LiveData < List < Stire > > getStiri( String idAlegere ) {
        return mGameMediator.getStiri( idAlegere );
    }

    public void insertVot( VotAnonim votAnonim ) {
        mGameMediator.insertVot( votAnonim );
    }
}
