package ro.unibuc.votingapp.domain;

import java.util.List;

import ro.unibuc.votingapp.data.VotAnonim;


public abstract class VoteRemoteRepository {
    protected VoteRemoteRepository() {
        //empty constructor for modifying access
    }

    protected abstract List<Object> getAlegeri();

    protected abstract List<Object> getLocatii();

    protected abstract List<Object> getCandidati();

    protected abstract List<Object> getStiri();

    protected abstract List<Object> getVoturi();

    protected abstract void insertVot(VotAnonim votAnonim);
}
