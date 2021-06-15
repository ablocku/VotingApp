package ro.unibuc.votingapp.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ro.unibuc.votingapp.R;
import ro.unibuc.votingapp.presentation.view.RecyclerViewActivity;

public final class InformalPollsFragment extends Fragment {
    private static final String PAV_GAME_INSPIRATION = "https://fmi.unibuc.ro/";

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        View root = super.onCreateView( inflater, container, savedInstanceState );

        //butonul cu care vom accesa panoul cu scoruri
        FloatingActionButton fab = requireView().findViewById( R.id.fabinformal );
        fab.setOnClickListener( view -> {
            Intent intent = new Intent( requireContext(), RecyclerViewActivity.class );
            Bundle bundle = new Bundle();
            bundle.putString( "tip", "informal" );
            intent.putExtras( bundle ); //Put your id to your next Intent
            requireContext().startActivity( intent );//cream o noua activitate pt utilizatorul specific
        } );

        return root;
    }

    public InformalPollsFragment( int contentLayoutId ) {
        super( contentLayoutId );
    }
}
