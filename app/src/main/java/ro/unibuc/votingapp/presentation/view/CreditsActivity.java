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
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_credits );
        VotingApplication.getApplication().addActivity( this );

        CardView view = findViewById( R.id.cardViewProgrammer );
        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( github ) );
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                getApplicationContext().startActivity( intent );
            }
        } );

        view = findViewById( R.id.cardViewCredits );
        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( google ) );
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                getApplicationContext().startActivity( intent );
            }
        } );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VotingApplication.getApplication().removeActivity( this );
    }
}
