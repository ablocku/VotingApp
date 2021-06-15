package ro.unibuc.votingapp.domain;


import ro.unibuc.votingapp.data.VotAnonim;

public abstract class VoteInMemoryRepository {
    protected VoteInMemoryRepository() {
        //empty constructor for modifying access
    }

    protected abstract void addInMemory( VotAnonim votAnonim );

    protected abstract VotAnonim removeInMemory();

    protected abstract int getNrOfElements();
}
