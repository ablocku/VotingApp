package ro.unibuc.votingapp.domain;

import android.content.Context;

import androidx.work.WorkManager;

import ro.unibuc.votingapp.data.source.InMemoryDataSource;
import ro.unibuc.votingapp.data.source.LocalVoteDataSource;
import ro.unibuc.votingapp.data.source.RemoteDataSource;


//se afla in .domain, deci ne poate da obiecte din tipul acelor clase
public final class VoteDependencyProvider {
    private final VoteInMemoryRepository inMemoryRepository;
    private final VoteRemoteRepository remoteRepository;
    private final VoteUseCase useCase;

    public VoteDependencyProvider( Context context ) {
        VoteLocalRepository localRepository = new LocalVoteDataSource( context );
        inMemoryRepository = new InMemoryDataSource();
        remoteRepository = new RemoteDataSource();

        WorkManager workManager1;           //necesar pt jUnitTests
        try {
            workManager1 = WorkManager.getInstance( context );
        } catch ( Exception e ) {
            workManager1 = null;
        }
        WorkManager workManager = workManager1;

        VoteMediator mediator = new VoteMediator( localRepository, workManager );
        useCase = new VoteUseCase( mediator );
    }

    public VoteInMemoryRepository provideInMemoryRepository() {
        return inMemoryRepository;
    }

    public VoteRemoteRepository provideRemoteRepository() {
        return remoteRepository;
    }

    public VoteUseCase provideUseCase() {
        return useCase;
    }
}
