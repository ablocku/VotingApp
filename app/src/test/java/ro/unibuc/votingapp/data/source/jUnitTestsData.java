package ro.unibuc.votingapp.data.source;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import ro.unibuc.votingapp.data.Utilizator;

@RunWith ( RobolectricTestRunner.class )
public class jUnitTestsData {
    private Context context;
    private Utilizator utilizator;
    private final String numeJucator = "myJUnitTest";


    @Before
    public void createGameEntity() {
        context = ApplicationProvider.getApplicationContext();
        utilizator = new Utilizator( numeJucator, numeJucator, numeJucator, numeJucator, numeJucator, numeJucator, numeJucator, numeJucator, numeJucator );
    }

    @Test
    public void test1() {
        AppDatabase db = Room.inMemoryDatabaseBuilder( context, AppDatabase.class ).allowMainThreadQueries().build();
        LocalVoteDataSource.VoteDao voteDao = db.voteDao();
        voteDao.insertUtilizator( utilizator );

        List < Utilizator > l = voteDao.getUtilizator(numeJucator);

        db.close();

        try {
            assert !l.isEmpty();
        } catch ( Exception e ) {
            assert false;
        }
    }
}
