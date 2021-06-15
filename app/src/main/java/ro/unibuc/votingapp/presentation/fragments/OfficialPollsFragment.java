package ro.unibuc.votingapp.presentation.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import ro.unibuc.votingapp.R;
import ro.unibuc.votingapp.presentation.view.RecyclerViewActivity;

public final class OfficialPollsFragment extends Fragment {
    private SharedPreferences sharedPreferences;      ///folosim sharedPreferences pt a stoca pt fiecare utilizator timpul total de joc (de cand a instalat aplicatia)
    private final static String pavGameSharedPreference = "pavGameSharedPreference";
    private Chronometer chronometer;
    private int lat;
    private int nrDala;
    private static final int nrGreseliMax = 5;
    private int nrGreseli;
    private final int[][] matrix = new int[ 256 ][ 256 ];
    private EditText input;
    private boolean started;
    private boolean finished;
    private Intent gameInProgressServiceIntent;

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        View root = super.onCreateView( inflater, container, savedInstanceState );

        return root;
    }

    public OfficialPollsFragment( int contentLayoutId ) {
        super( contentLayoutId );
    }

    @Override
    public void onViewCreated( @NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
        //butonul cu care vom accesa panoul cu scoruri
        FloatingActionButton fab = view.findViewById( R.id.fabformal );
        fab.setOnClickListener( myView -> {
            Intent intent = new Intent( requireContext(), RecyclerViewActivity.class );
            Bundle bundle = new Bundle();
            bundle.putString( "tip", "formal" );
            intent.putExtras( bundle ); //Put your id to your next Intent
            requireContext().startActivity( intent );//cream o noua activitate pt utilizatorul specific
        } );
    }

    //    @Override
//    public void onActivityCreated( @Nullable Bundle savedInstanceState ) {
//        super.onActivityCreated( savedInstanceState );
//
//        //obtin din sharedPreferances cat timp am avut in app pana acum, pt a seta cronometrul
//        long timeSpent;   //timpul petrecut(cu precizie de minute) in joc
//        sharedPreferences = super.requireContext().getSharedPreferences( pavGameSharedPreference, Context.MODE_PRIVATE );
//        try {
//            String s = sharedPreferences.getString( VotingAppViewModel.getUserName(), "0" );//preluam timpul utilizatorului
//            timeSpent = Long.parseLong( s );
//        } catch ( Exception e ) {
//            Timber.d( e );
//            timeSpent = 0;
//        }
//
//        //setez cronometrul
//        chronometer = requireView().findViewById( R.id.chronometer );
//        chronometer.setBase( SystemClock.elapsedRealtime() - timeSpent * 60000 );//asa se initializeaza cronometrul
//        chronometer.start();
//
//        //intent pt serviciul din foreground ce se creeaza cand e un joc in desfasurare
//        gameInProgressServiceIntent = new Intent( requireContext(), VotingAppService.class );
//    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        //luam noul timpul total petrecut in aplicatie (cat era initial + cat s-a jucat utilizatorul acum)
//        long newTime = SystemClock.elapsedRealtime() - chronometer.getBase();//milisecunde petrecut in total, dupa acest joc
//        newTime /= 1000; //secunde
//        newTime /= 60; //minute
//
//        //convertim acest numar (valoare rotunjita la minute) tinut pe Long la json
//        //si ii fac update in shared preferances
//        Gson gson = new Gson();
//        String json = gson.toJson( newTime );       ///convertim acest timp la un json string
//        sharedPreferences.edit().putString( VotingAppViewModel.getUserName() + "", json ).apply(); ///updatam valoarea in shared preferances
//
//    }


}
