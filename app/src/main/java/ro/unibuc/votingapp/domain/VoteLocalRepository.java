package ro.unibuc.votingapp.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.VotAnonim;
import ro.unibuc.votingapp.data.Utilizator;

public abstract class VoteLocalRepository {
    protected VoteLocalRepository() {
        //empty constructor for modifying access
    }

    protected abstract LiveData < List < Candidat > > getCandidati( String idAlegere );

    protected abstract LiveData < List < Alegere > > getAlegeri( String idLocatie, String tip );

    protected abstract LiveData < List < Locatie > > getLocatii();

    protected abstract Stire getStireById( String idAlegere, String idStire );

    protected abstract LiveData < List < Stire > > getStiri();

    protected abstract void insertLocatie( Locatie locatie );

    protected abstract void insertAlegere( Alegere alegere );

    protected abstract void insertCandidat( Candidat candidat );

    protected abstract void insertVot( VotAnonim votAnonim );

    protected abstract void insertStire( Stire stire );

    protected abstract List < Utilizator > getUtilizator( String CNP );

    protected abstract void insertUtilizator( Utilizator utilizator );
}
