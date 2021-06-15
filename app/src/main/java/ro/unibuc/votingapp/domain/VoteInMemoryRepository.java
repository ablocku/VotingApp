package ro.unibuc.votingapp.domain;


import ro.unibuc.votingapp.data.Utilizator;
import ro.unibuc.votingapp.data.VotAnonim;

public abstract class VoteInMemoryRepository {
    protected VoteInMemoryRepository() {
        //empty constructor for modifying access
    }

    protected abstract void addUserInMemory(Utilizator utilizator);

    protected abstract Utilizator removeUserInMemory();

    protected abstract int getNrOfUsers();

    protected abstract void addInMemory(VotAnonim votAnonim );

    protected abstract VotAnonim removeInMemory();

    protected abstract int getNrOfElements();
}
