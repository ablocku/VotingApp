package ro.unibuc.votingapp.presentation.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;

import ro.unibuc.votingapp.VotingApplication;
import ro.unibuc.votingapp.R;

public final class CreditsActivity extends Activity {
    private final static String github = "https://github.com/ReksioCroft/";
    private final static String google = "https://events.withgoogle.com/atelierul-digital-pentru-programatori/";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VotingApplication.getApplication().removeActivity( this );
    }
}
