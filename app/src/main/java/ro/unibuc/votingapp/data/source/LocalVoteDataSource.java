package ro.unibuc.votingapp.data.source;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.VotAnonim;
import ro.unibuc.votingapp.domain.VoteLocalRepository;
import ro.unibuc.votingapp.data.Utilizator;
import timber.log.Timber;

public final class LocalVoteDataSource extends VoteLocalRepository {
    final VoteDao mVoteDao;

    public LocalVoteDataSource( Context context ) {
        super();
        mVoteDao = AppDatabase.getAppDatabase( context ).voteDao();
    }

    @Override
    protected LiveData < List < Candidat > > getCandidati( String idAlegere ) {
        return mVoteDao.getCandidati( idAlegere );
    }

    @Override
    protected LiveData < List < Alegere > > getAlegeri( String idLocatie, String tip ) {
        return mVoteDao.getAlegeri( idLocatie, tip );
    }

    @Override
    protected LiveData < List < Locatie > > getLocatii() {
        return mVoteDao.getLocatii();
    }

    @Override
    protected Stire getStireById( String idAlegere, String idStire ) {
        return mVoteDao.getStireById( idAlegere, idStire );
    }

    @Override
    protected LiveData < List < Stire > > getStiri() {
        return mVoteDao.getStiri();
    }


    @Override
    protected List < Utilizator > getUtilizator( String CNP ) {
        return mVoteDao.getUtilizator( CNP );
    }

    @Override
    protected void insertUtilizator( Utilizator utilizator ) {
        AppDatabase.databaseWriteExecutor.execute( () -> {
            try {
                mVoteDao.insertUtilizator( utilizator );
            } catch ( Exception e ) {
                Timber.e( e );
            }
        } );
    }

    @Override
    protected void insertLocatie( Locatie locatie ) {
        AppDatabase.databaseWriteExecutor.execute( () -> {
            try {
                mVoteDao.insertLocatie( locatie );
            } catch ( Exception e ) {
                Timber.e( e );
            }
        } );
    }

    @Override
    protected void insertAlegere( Alegere alegere ) {
        AppDatabase.databaseWriteExecutor.execute( () -> {
            try {
                mVoteDao.insertAlegere( alegere );
            } catch ( Exception e ) {
                Timber.e( e );
            }
        } );
    }

    @Override
    protected void insertCandidat( Candidat candidat ) {
        AppDatabase.databaseWriteExecutor.execute( () -> {
            try {
                mVoteDao.insertCandidat( candidat );
            } catch ( Exception e ) {
                Timber.e( e );
            }
        } );
    }

    @Override
    protected void insertVot( VotAnonim votAnonim ) {
        AppDatabase.databaseWriteExecutor.execute( () -> {
            try {
                mVoteDao.insertVot( votAnonim );
            } catch ( Exception e ) {
                Timber.e( e );
            }
        } );
    }

    @Override
    protected void insertStire( Stire stire ) {
        AppDatabase.databaseWriteExecutor.execute( () -> {
            try {
                mVoteDao.insertStire( stire );
            } catch ( Exception e ) {
                Timber.e( e );
            }
        } );
    }

    @Dao
    interface VoteDao {
        @Query ( "SELECT DISTINCT Candidat.idCandidat,Candidat.idAlegere, numeCandidat, cast (count(*) as text) as observatii from Candidat, VotAnonim where Candidat.idCandidat=VotAnonim.idCandidat and Candidat.idAlegere=VotAnonim.idAlegere group by Candidat.idAlegere, numeCandidat, observatii having Candidat.idAlegere=:idAlegere " )
        LiveData < List < Candidat > > getCandidati( String idAlegere );

        @Query ( "SELECT * FROM Alegere Where idLocatie=:idLocatie and tipVot=:tip" )
        LiveData < List < Alegere > > getAlegeri( String idLocatie, String tip );

        @Query ( "SELECT * FROM Locatie" )
        LiveData < List < Locatie > > getLocatii();

        @Query ( "SELECT * FROM Stire where idAlegere=:idAlegere and idStire=:idStire" )
        Stire getStireById( String idAlegere, String idStire );

        @Query ( "SELECT * FROM Stire" )
        LiveData < List < Stire > > getStiri();

        @Query ( "SELECT * FROM Utilizator where idUtilizator=:CNP" )
        List < Utilizator > getUtilizator( String CNP );

        @Insert ( onConflict = OnConflictStrategy.REPLACE )
        void insertUtilizator( Utilizator utilizator );

        @Insert ( onConflict = OnConflictStrategy.IGNORE )
        void insertLocatie( Locatie locatie );

        @Insert ( onConflict = OnConflictStrategy.IGNORE )
        void insertAlegere( Alegere alegere );

        @Insert ( onConflict = OnConflictStrategy.IGNORE )
        void insertCandidat( Candidat candidat );

        @Insert ( onConflict = OnConflictStrategy.IGNORE )
        void insertVot( VotAnonim votAnonim );

        @Insert ( onConflict = OnConflictStrategy.IGNORE )
        void insertStire( Stire stire );
    }
}
