package ro.unibuc.votingapp.domain;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.VotAnonim;
import ro.unibuc.votingapp.data.source.InMemoryDataSource;
import timber.log.Timber;

final class VoteMediator {
    private final VoteLocalRepository localRepository;
    private final WorkManager workManager;
    private final Data getDataforBuilder;
    private final Data postDataforBuilder;

    VoteMediator( VoteLocalRepository localRepository,
                  WorkManager workManager ) {

        this.localRepository = localRepository;
        this.workManager = workManager;

        //modul pe care il transmitem la worker
        this.getDataforBuilder = ( new Data.Builder().putString( "mode", "get" ) ).build();
        this.postDataforBuilder = ( new Data.Builder().putString( "mode", "post" ) ).build();

        //Vrem ca primul get sa se faca instant cand se deschide aplicatia
        getFromRemoteRepository();

        //De asemenea, vrem sa facem get pe parcurs
        setPeriodicRequests();
    }

    LiveData < List < Candidat > > getCandidati( String idAlegere ) {
        return localRepository.getCandidati( idAlegere );
    }

    LiveData < List < Alegere > > getAlegeri( String idLocatie ) {
        return localRepository.getAlegeri( idLocatie );
    }

    LiveData < List < Locatie > > getLocatii() {
        return localRepository.getLocatii();
    }

    LiveData < List < Stire > > getStiri( String idAlegere ) {
        return localRepository.getStiri( idAlegere );
    }

    void insertVot( VotAnonim votAnonim ) {
        //adaugam votul in firebase db
        postVoteToRemoteRepository( votAnonim );

        //Inseram votul in roomDatabase
        localRepository.insertVot( votAnonim );
    }

    private void setPeriodicRequests() {
        //In caz ca vreun vot nu ajunge in Firebase, vrem sa il sincronizam
        //fara a fi necesar sa se deschidem RecucleViewActivity
        try {
            PeriodicWorkRequest postWorkRequest =
                    new PeriodicWorkRequest.Builder( VoteWorker.class,
                            PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS )
                            .setInputData( postDataforBuilder )
                            .build();
            workManager.enqueueUniquePeriodicWork( "postInMemoryVotes",
                    ExistingPeriodicWorkPolicy.KEEP, postWorkRequest );
            // keep - va fi creat o singura data si va rula la fiecare 15min, alte instantieri fiind ignorate
            // ex: cand se acceseaza RecylceViewActivity sau se insereaza un vot
        } catch ( Exception e ) {
            Timber.d( e );
        }
    }

    private void getFromRemoteRepository() {
        //este apelat de cate ori se acceseaza RecycleView activity
        try {
            OneTimeWorkRequest downloadWorkRequest =
                    new OneTimeWorkRequest.Builder( VoteWorker.class )
                            .setInputData( getDataforBuilder )
                            .build();
            workManager.enqueue( downloadWorkRequest );
        } catch ( Exception e ) {
            Timber.d( e );
        }
    }

    private void postVoteToRemoteRepository( VotAnonim votAnonim ) {
        //inseram votul in coada repo-ului local
        VoteInMemoryRepository voteInMemoryRepository = new InMemoryDataSource();
        voteInMemoryRepository.addInMemory( votAnonim );

        //dupa ce am inserat acest vot, il vom trimite (pe el si ce mai e in coada) in repo-ul firebase, prin worker
        try {
            OneTimeWorkRequest postWorkRequest =
                    new OneTimeWorkRequest.Builder( VoteWorker.class )
                            .setInputData( postDataforBuilder )
                            .build();
            workManager.enqueue( postWorkRequest );
        } catch ( Exception e ) {
            Timber.d( e );
        }
    }
}
