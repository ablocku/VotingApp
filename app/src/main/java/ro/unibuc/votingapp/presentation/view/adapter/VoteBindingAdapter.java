package ro.unibuc.votingapp.presentation.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.presentation.VotingAppViewModel;


public final class VoteBindingAdapter {
    @BindingAdapter ( "pollsAdapter" )
    public static void recycleViewSetAdapter( RecyclerView mRecyclerViewGames, LocationAdapter locationAdapter ) {
        if ( mRecyclerViewGames.getAdapter() == null ) {
            // set the adapter to the recycler view
            mRecyclerViewGames.setAdapter( locationAdapter );
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

    @BindingAdapter ( { "VoteViewModel", "location" } )
    public static void RecycleViewAlegereBinding( RecyclerView mRecyclerViewGames, VotingAppViewModel votingAppViewModel, @NonNull String locationId ) {
        votingAppViewModel.getAlegeri( locationId ).observeForever( new Observer < List < Alegere > >() {
            @Override
            public void onChanged( @Nullable final List < Alegere > alegeri ) {
                //suntem siguri ca adaptorul nostru este de tipul GameAdapter
                AlegereAdapter alegereAdapter = ( AlegereAdapter ) mRecyclerViewGames.getAdapter();
                if ( alegereAdapter != null )
                    alegereAdapter.setGames( alegeri );
            }
        } );
    }
}
