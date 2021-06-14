package ro.unibuc.votingapp.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.VotAnonim;


public abstract class VoteLocalRepository {
    protected VoteLocalRepository() {
        //empty constructor for modifying access
    }

    protected abstract LiveData<List<Candidat>> getCandidati(String idAlegere);

    protected abstract LiveData<List<Alegere>> getAlegeri(String idLocatie);

    protected abstract LiveData<List<Locatie>> getLocatii();

    protected abstract LiveData<List<Stire>> getStiri(String idAlegere);

    protected abstract void insertLocatie(Locatie locatie);

    protected abstract void insertAlegere(Alegere alegere);

    protected abstract void insertCandidat(Candidat candidat);

    protected abstract void insertVot(VotAnonim votAnonim);

    protected abstract void insertStire(Stire stire);
}
