package ro.unibuc.votingapp.data.source;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ro.unibuc.votingapp.data.*;

@Database ( entities = { Alegere.class, Candidat.class, Locatie.class, Stire.class, VotAnonim.class }, version = 1 )
abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool( NUMBER_OF_THREADS );

    abstract LocalVoteDataSource.VoteDao voteDao();

    static AppDatabase getAppDatabase( final Context context ) {
        if ( INSTANCE == null ) {
            synchronized ( AppDatabase.class ) {
                INSTANCE = Room.databaseBuilder( context.getApplicationContext(),
                        AppDatabase.class,
                        "vote-db" )
                        .build();
            }
        }
        return INSTANCE;
    }
}
