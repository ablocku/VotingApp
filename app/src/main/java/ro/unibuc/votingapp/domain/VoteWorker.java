package ro.unibuc.votingapp.domain;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.List;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.VotAnonim;
import ro.unibuc.votingapp.data.source.InMemoryDataSource;
import ro.unibuc.votingapp.data.source.LocalVoteDataSource;
import ro.unibuc.votingapp.data.source.RemoteDataSource;
import timber.log.Timber;

public final class VoteWorker extends Worker {
    private final Context context;

    VoteWorker( @NonNull Context context, @NonNull WorkerParameters workerParams ) {
        super( context, workerParams );
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        String value = data.getString( "mode" );

        if ( "get".equals( value ) ) {
            Timber.d( "GET Operation" );
            VoteRemoteRepository voteRemoteRepository = new RemoteDataSource();
            VoteLocalRepository voteLocalRepository = new LocalVoteDataSource( context );

            List < Locatie > locatii = voteRemoteRepository.getLocatii();
            for ( Locatie locatie : locatii ) {
                voteLocalRepository.insertLocatie( locatie );
            }
            Timber.d( "worker finished downloading locatii" );


            List < Alegere > alegeri = voteRemoteRepository.getAlegeri();
            for ( Alegere alegere : alegeri ) {
                voteLocalRepository.insertAlegere( alegere );
            }
            Timber.d( "worker finished downloading alegeri" );

            List < Stire > stiri = voteRemoteRepository.getStiri();
            for ( Stire stire : stiri ) {
                voteLocalRepository.insertStire( stire );
            }
            Timber.d( "worker finished downloading stiri" );

            List < Candidat > candidati = voteRemoteRepository.getCandidati();
            for ( Candidat candidat : candidati ) {
                voteLocalRepository.insertCandidat( candidat );
            }
            Timber.d( "worker finished downloading locations" );

            List < VotAnonim > voturi = voteRemoteRepository.getVoturi();
            for ( VotAnonim votAnonim : voturi ) {
                voteLocalRepository.insertVot( votAnonim );
            }
            Timber.d( "worker finished downloading locations" );

        } else if ( "post".equals( value ) ) {
            Timber.d( "SYNC Operation" );
            VoteInMemoryRepository voteInMemoryRepository = new InMemoryDataSource();
            VoteRemoteRepository voteRemoteRepository = new RemoteDataSource();

            final int nrOfSyncs = voteInMemoryRepository.getNrOfElements();
            for ( int i = 0; i < nrOfSyncs; i++ ) {         ///pt a nu face ciclu infinit; ex:nu merge netul=> worst case n insert failed
                voteRemoteRepository.insertVot( voteInMemoryRepository.removeInMemory() );
            }
            Timber.d( "worker finished posting votes" );
        }

        return Result.success();
    }
}
