package ro.unibuc.votingapp.presentation.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import ro.unibuc.votingapp.R;
import ro.unibuc.votingapp.VotingApplication;
import ro.unibuc.votingapp.presentation.VotingAppViewModel;
import ro.unibuc.votingapp.presentation.VotingAppViewModelFactory;
import ro.unibuc.votingapp.presentation.view.adapter.LocationAdapter;
import ro.unibuc.votingapp.presentation.view.adapter.VoteBindingAdapter;


public final class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_recycler_view );

        //butonul de sus pentru a inchide activitatea
        ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null )
            actionBar.setDisplayHomeAsUpEnabled( true );

        //adaugam activitatea in lista
        VotingApplication.getApplication().addActivity( this );

        // get recycler view from xml layout
        RecyclerView mRecyclerViewGames = findViewById( R.id.recycler_view_contacts_1 );

        //daca suntem pe jocurile unui singur utilizator, va fi setat fals la verificare
        //pentru a nu permite sa se creeze activitati la infinit pt a afisa jocurile unui utilizator
        boolean setOnClickListenerOnViewCards = true;

        //verificam sa vedem daca vrem sa afisam doar pt un anumit utilizator
        Bundle b = getIntent().getExtras();
        if ( b != null ) {
            String tip = b.getString( "tip" );
            if ( tip != null ) {
                if ( tip.equals( "news" ) ) {

                } else {
                    String specificLocation = b.getString( "specificLocation" );
//            setOnClickListenerOnViewCards = false;

                    if ( specificLocation == null ) {
                        // get the adapter instance
                        final LocationAdapter gamesAdapter = new LocationAdapter( mRecyclerViewGames.getContext(), setOnClickListenerOnViewCards );

                        //binding pentru a seta gameAdaptorul la RecyclerView-ul nostru
                        VoteBindingAdapter.recycleViewSetAdapter( mRecyclerViewGames, gamesAdapter );

                        //obtinem ViewModel
                        VotingAppViewModel votingAppViewModel = new ViewModelProvider( this, new VotingAppViewModelFactory( VotingApplication.getApplication() ) ).get( VotingAppViewModel.class );

//        binding pentru a prelua datele din repository
                        VoteBindingAdapter.RecycleViewLocationBinding( mRecyclerViewGames, votingAppViewModel );
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VotingApplication.getApplication().removeActivity( this );
    }


    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        if ( item.getItemId() == android.R.id.home ) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
