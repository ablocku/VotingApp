package ro.unibuc.votingapp.data.source;


import ro.unibuc.votingapp.data.VotAnonim;
import ro.unibuc.votingapp.domain.VoteInMemoryRepository;

public final class InMemoryDataSource extends VoteInMemoryRepository {
    private final InMemoryTemporaryStorage inMemoryTemporaryStorage;

    public InMemoryDataSource() {
        super();
        inMemoryTemporaryStorage = new InMemoryTemporaryStorage();
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
