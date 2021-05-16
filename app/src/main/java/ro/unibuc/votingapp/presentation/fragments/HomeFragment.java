package ro.unibuc.votingapp.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ro.unibuc.votingapp.R;
import ro.unibuc.votingapp.presentation.VotingAppViewModel;

public final class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        View root = super.onCreateView( inflater, container, savedInstanceState );
        if ( root != null ) {
            TextView textView = root.findViewById( R.id.text_home );
            textView.setText( String.format( getString( R.string.welcome ), VotingAppViewModel.getUserName() ) );
        }
        return root;
    }

    public HomeFragment( int contentLayoutId ) {
        super( contentLayoutId );
    }
}
