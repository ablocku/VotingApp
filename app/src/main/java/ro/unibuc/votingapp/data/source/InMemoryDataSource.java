package ro.unibuc.votingapp.data.source;


import ro.unibuc.votingapp.data.Utilizator;
import ro.unibuc.votingapp.data.VotAnonim;
import ro.unibuc.votingapp.domain.VoteInMemoryRepository;

public final class InMemoryDataSource extends VoteInMemoryRepository {
    private final InMemoryTemporaryStorage inMemoryTemporaryStorage;
    private final InMemoryUserTemporaryStorage inMemoryUserTemporaryStorage;

    public InMemoryDataSource() {
        super();
        inMemoryTemporaryStorage = new InMemoryTemporaryStorage();
        inMemoryUserTemporaryStorage = new InMemoryUserTemporaryStorage();
    }

    @Override
    protected void addUserInMemory(Utilizator utilizator) {
        inMemoryUserTemporaryStorage.addUserInMemory( utilizator );
    }

    @Override
    protected Utilizator removeUserInMemory() {
        return inMemoryUserTemporaryStorage.removeUserInMemory();
    }

    @Override
    protected int getNrOfUsers() {
        return inMemoryUserTemporaryStorage.getNrOfUsers();
    }

    @Override
    protected void addInMemory( VotAnonim votAnonim ) {
        inMemoryTemporaryStorage.addInMemory( votAnonim );
    }

    @Override
    protected VotAnonim removeInMemory() {
        return inMemoryTemporaryStorage.removeInMemory();
    }

    @Override
    protected int getNrOfElements() {
        return inMemoryTemporaryStorage.getNrOfElements();
    }
}
