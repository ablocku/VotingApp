package ro.unibuc.votingapp.presentation.view.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

    @BindingAdapter ( { "VoteViewModel", "location" } )
    public static void RecycleViewLocationBindingGames( RecyclerView mRecyclerViewGames, VotingAppViewModel votingAppViewModel, @Nullable String location ) {
        //daca am primit un string null, facem bind cu toate jocurile din repo
        if ( location == null ) {
            votingAppViewModel.getLocatii().observeForever( new Observer < List < Locatie > >() {
                @Override
                public void onChanged( @Nullable final List < Locatie > locatii ) {
                    //suntem siguri ca adaptorul nostru este de tipul GameAdapter
                    LocationAdapter locationAdapter = ( LocationAdapter ) mRecyclerViewGames.getAdapter();
                    if ( locationAdapter != null )
                        locationAdapter.setGames( locatii );
                }
            } );
        }  //altfel, afisam jocurile unui anumite utilizator
//        else {
//            votingAppViewModel.getSpecificGamesbyUserName( user ).observeForever( new Observer < List < GameEntity > >() {
//                @Override
//                public void onChanged( @Nullable final List < GameEntity > games ) {
//                    GamesAdapter gamesAdapter = ( GamesAdapter ) mRecyclerViewGames.getAdapter();
//                    if ( gamesAdapter != null )
//                        gamesAdapter.setGames( games );
//                }
//            } );
//        }
    }
}
