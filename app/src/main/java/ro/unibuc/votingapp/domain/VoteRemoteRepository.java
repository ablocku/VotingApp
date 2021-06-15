package ro.unibuc.votingapp.domain;

import java.util.List;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.Utilizator;
import ro.unibuc.votingapp.data.VotAnonim;


public abstract class VoteRemoteRepository {
    protected VoteRemoteRepository() {
        //empty constructor for modifying access
    }

    protected abstract List<Utilizator> getUtilizatori();

    protected abstract void insertUtilizator(Utilizator utilizator);

    protected abstract List < Alegere > getAlegeri();

    protected abstract List < Locatie > getLocatii();

    protected abstract List < Candidat > getCandidati();

    protected abstract List < Stire > getStiri();

    protected abstract List < VotAnonim > getVoturi();

    protected abstract void insertVot( VotAnonim votAnonim );
}
