package ro.unibuc.votingapp.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ro.unibuc.votingapp.R;

public final class InformalPollsFragment extends Fragment {
    private static final String PAV_GAME_INSPIRATION = "https://fmi.unibuc.ro/";

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        View root = super.onCreateView( inflater, container, savedInstanceState );
        if ( root != null ) {
            WebView mWebViewExample = root.findViewById( R.id.webview_example );
            mWebViewExample.loadUrl( PAV_GAME_INSPIRATION );
        }
        return root;
    }

    public InformalPollsFragment(int contentLayoutId ) {
        super( contentLayoutId );
    }
}
