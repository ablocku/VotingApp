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
import ro.unibuc.votingapp.presentation.view.databinding.AlegereAdapter;
import ro.unibuc.votingapp.presentation.view.databinding.CandidatAdapter;
import ro.unibuc.votingapp.presentation.view.databinding.LocationAdapter;
import ro.unibuc.votingapp.presentation.view.databinding.NewsAdapter;
import ro.unibuc.votingapp.presentation.view.databinding.VoteBindingAdapter;


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

        //verificam sa vedem daca vrem sa afisam doar pt un anumit utilizator
        Bundle b = getIntent().getExtras();
        if ( b != null ) {
            String tip = b.getString( "tip" );
            if ( tip != null ) {
                //obtinem ViewModel
                VotingAppViewModel votingAppViewModel = new ViewModelProvider( this, new VotingAppViewModelFactory( VotingApplication.getApplication() ) ).get( VotingAppViewModel.class );
                if ( tip.equals( "news" ) ) {
                    final NewsAdapter newsAdapter = new NewsAdapter( mRecyclerViewGames.getContext() );
                    VoteBindingAdapter.recycleViewSetNewsAdapter( mRecyclerViewGames, newsAdapter );
                    VoteBindingAdapter.recycleViewNewsBinding( mRecyclerViewGames, votingAppViewModel);
                } else {
                    String specificLocation = b.getString( "specificLocation" );

                    if ( specificLocation == null ) {
                        final LocationAdapter locationAdapter = new LocationAdapter( mRecyclerViewGames.getContext(), tip );
                        VoteBindingAdapter.recycleViewSetLocationAdapter( mRecyclerViewGames, locationAdapter );
                        VoteBindingAdapter.recycleViewLocationBinding( mRecyclerViewGames, votingAppViewModel );
                    } else {
                        final String idAlegere = b.getString( "idAlegere" );

                        if ( idAlegere == null ) {
                            final AlegereAdapter alegereAdapter = new AlegereAdapter( mRecyclerViewGames.getContext() );
                            VoteBindingAdapter.recycleViewSetAlegereAdapter( mRecyclerViewGames, alegereAdapter );
                            VoteBindingAdapter.recycleViewAlegereBinding( mRecyclerViewGames, votingAppViewModel, specificLocation, tip );
                        } else {
                            final CandidatAdapter candidatAdapter = new CandidatAdapter( mRecyclerViewGames.getContext() );
                            VoteBindingAdapter.recycleViewSetCandidatAdapter( mRecyclerViewGames, candidatAdapter );
                            VoteBindingAdapter.recycleViewCandidatBinding( mRecyclerViewGames, votingAppViewModel, tip, idAlegere );
                        }
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
