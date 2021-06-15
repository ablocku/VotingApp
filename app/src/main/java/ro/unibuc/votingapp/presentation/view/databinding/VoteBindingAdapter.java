package ro.unibuc.votingapp.presentation.view.databinding;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ro.unibuc.votingapp.presentation.VotingAppViewModel;


public final class VoteBindingAdapter {
    @BindingAdapter ( "pollsAdapter" )
    public static void recycleViewSetLocationAdapter( RecyclerView mRecyclerViewGames, LocationAdapter locationAdapter ) {
        if ( mRecyclerViewGames.getAdapter() == null ) {
            // set the adapter to the recycler view
            mRecyclerViewGames.setAdapter( locationAdapter );
            // define and set layout manager
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( mRecyclerViewGames.getContext() );
            mRecyclerViewGames.setLayoutManager( layoutManager );
        }
    }

    @BindingAdapter ( "pollsAdapter" )
    public static void recycleViewSetNewsAdapter( RecyclerView mRecyclerViewGames, NewsAdapter newsAdapter ) {
        if ( mRecyclerViewGames.getAdapter() == null ) {
            // set the adapter to the recycler view
            mRecyclerViewGames.setAdapter( newsAdapter );
            // define and set layout manager
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( mRecyclerViewGames.getContext() );
            mRecyclerViewGames.setLayoutManager( layoutManager );
        }
    }

    @BindingAdapter ( "pollsAdapter" )
    public static void recycleViewSetAlegereAdapter( RecyclerView mRecyclerViewGames, AlegereAdapter alegereAdapter ) {
        if ( mRecyclerViewGames.getAdapter() == null ) {
            // set the adapter to the recycler view
            mRecyclerViewGames.setAdapter( alegereAdapter );
            // define and set layout manager
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( mRecyclerViewGames.getContext() );
            mRecyclerViewGames.setLayoutManager( layoutManager );
        }
    }

    @BindingAdapter ( { "VoteViewModel" } )
    public static void RecycleViewLocationBinding( RecyclerView mRecyclerViewGames, VotingAppViewModel votingAppViewModel ) {
        votingAppViewModel.getLocatii().observeForever( locatii -> {
            LocationAdapter locationAdapter = ( LocationAdapter ) mRecyclerViewGames.getAdapter();
            if ( locationAdapter != null )
                locationAdapter.setGames( locatii );
        } );
    }

    @BindingAdapter ( { "VoteViewModel", "idAlegere", "idNews" } )
    public static void RecycleViewNewsBinding( RecyclerView mRecyclerViewGames, VotingAppViewModel votingAppViewModel, @NonNull String idAlegere, @NonNull String idNews ) {
        votingAppViewModel.getStireById( idAlegere, idNews ).observeForever( stiri -> {
            NewsAdapter newsAdapter = ( NewsAdapter ) mRecyclerViewGames.getAdapter();
            if ( newsAdapter != null )
                newsAdapter.setGames( stiri );
        } );
    }

    @BindingAdapter ( { "VoteViewModel", "location", "tip" } )
    public static void RecycleViewAlegereBinding( RecyclerView mRecyclerViewGames, VotingAppViewModel votingAppViewModel, @NonNull String locationId, @NonNull String tip ) {
        votingAppViewModel.getAlegeri( locationId, tip ).observeForever( alegeri -> {
            //suntem siguri ca adaptorul nostru este de tipul GameAdapter
            AlegereAdapter alegereAdapter = ( AlegereAdapter ) mRecyclerViewGames.getAdapter();
            if ( alegereAdapter != null )
                alegereAdapter.setGames( alegeri );
        } );
    }
}
