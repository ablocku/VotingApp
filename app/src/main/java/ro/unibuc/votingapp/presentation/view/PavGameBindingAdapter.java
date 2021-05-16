package ro.unibuc.votingapp.presentation.view;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public final class PavGameBindingAdapter {
    @BindingAdapter("pollsAdapter")
    public static void recycleViewSetAdapter(RecyclerView mRecyclerViewGames, RecyclerView.Adapter gamesAdapter) {
        if (mRecyclerViewGames.getAdapter() == null) {
            // set the adapter to the recycler view
            mRecyclerViewGames.setAdapter(gamesAdapter);
            // define and set layout manager
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mRecyclerViewGames.getContext());
            mRecyclerViewGames.setLayoutManager(layoutManager);
        }
    }
}
