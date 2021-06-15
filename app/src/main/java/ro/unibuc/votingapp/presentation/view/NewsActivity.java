package ro.unibuc.votingapp.presentation.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ro.unibuc.votingapp.R;

public class NewsActivity extends AppCompatActivity {
    String titlu = "";
    String continut = "";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news );
        Bundle b = getIntent().getExtras();
        if ( b != null ) {
            titlu = b.getString( "titlu" );
            continut = b.getString( "content" );
            TextView textView = findViewById( R.id.newsTitle );
            textView.setText( titlu );
            TextView textView2 = findViewById( R.id.newsContent );
            textView2.setText( continut );
        }
    }
}