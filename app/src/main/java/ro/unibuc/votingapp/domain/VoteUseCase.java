package ro.unibuc.votingapp.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.Utilizator;
import ro.unibuc.votingapp.data.VotAnonim;


public final class VoteUseCase {
    private final VoteMediator mGameMediator;

    VoteUseCase( VoteMediator mediator ) {
        this.mGameMediator = mediator;
    }

    public LiveData < List < Candidat > > getCandidati( String idAlegere ) {
        return mGameMediator.getCandidati( idAlegere );
    }

    public LiveData < List < Alegere > > getAlegeri( String idLocatie, String tip ) {
        return mGameMediator.getAlegeri( idLocatie ,tip);
    }

    public LiveData < List < Locatie > > getLocatii() {
        return mGameMediator.getLocatii();
    }

    public Stire getStireById( String idAlegere, String idStire ) {
        return mGameMediator.getStireById( idAlegere, idStire );
    }

    public LiveData < List < Stire > > getStiri( ) {
        return mGameMediator.getStiri(  );
    }

    public void insertVot( VotAnonim votAnonim ) {
        mGameMediator.insertVot( votAnonim );
    }

    public void insertUtilizator( Utilizator utilizator ) {
        mGameMediator.insertUtilizator( utilizator );
    }

}
