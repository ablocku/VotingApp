package ro.unibuc.votingapp.data.source;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ro.unibuc.votingapp.data.Alegere;
import ro.unibuc.votingapp.data.Candidat;
import ro.unibuc.votingapp.data.Locatie;
import ro.unibuc.votingapp.data.Stire;
import ro.unibuc.votingapp.data.VotAnonim;
import ro.unibuc.votingapp.domain.VoteRemoteRepository;
import timber.log.Timber;

public final class RemoteDataSource extends VoteRemoteRepository {
    private final RetrofitApi api;

    public RemoteDataSource() {
        super();
        api = RetrofitApi.createApi();
    }

    @Override
    protected List < Alegere > getAlegeri() {
        List < Alegere > alegeri = new ArrayList <>();
        Gson gson = new Gson();
        try {
            JsonObject response = api.getAlegeri().execute().body();
            if ( response != null ) {
                for ( String jsonObjectKeys : response.keySet() ) {
                    JsonObject jsonObject = response.getAsJsonObject( jsonObjectKeys );
                    Alegere alegere = gson.fromJson( jsonObject, Alegere.class );
                    if ( alegere != null )
                        alegeri.add( alegere );
                }
            }
        } catch ( Exception e ) {
            Timber.d( e, "Something happened" );
        }
        return alegeri;
    }

    @Override
    protected List < Locatie > getLocatii() {
        List < Locatie > locatii = new ArrayList <>();
        Gson gson = new Gson();
        try {
            JsonObject response = api.getLocatii().execute().body();
            if ( response != null ) {
                for ( String jsonObjectKeys : response.keySet() ) {
                    JsonObject jsonObject = response.getAsJsonObject( jsonObjectKeys );
                    Locatie locatie = gson.fromJson( jsonObject, Locatie.class );
                    if ( locatie != null )
                        locatii.add( locatie );
                }
            }
        } catch ( Exception e ) {
            Timber.d( e, "Something happened" );
        }
        return locatii;
    }

    @Override
    protected List < Candidat > getCandidati() {
        List < Candidat > candidati = new ArrayList <>();
        Gson gson = new Gson();
        try {
            JsonObject response = api.getCandidati().execute().body();
            if ( response != null ) {
                for ( String jsonObjectKeys : response.keySet() ) {
                    JsonObject jsonObject = response.getAsJsonObject( jsonObjectKeys );
                    Candidat candidat = gson.fromJson( jsonObject, Candidat.class );
                    if ( candidat != null )
                        candidati.add( candidat );
                }
            }
        } catch ( Exception e ) {
            Timber.d( e, "Something happened" );
        }
        return candidati;
    }

    @Override
    protected List < Stire > getStiri() {
        List < Stire > stiri = new ArrayList <>();
        Gson gson = new Gson();
        try {
            JsonObject response = api.getAlegeri().execute().body();
            if ( response != null ) {
                for ( String jsonObjectKeys : response.keySet() ) {
                    JsonObject jsonObject = response.getAsJsonObject( jsonObjectKeys );
                    Stire stire = gson.fromJson( jsonObject, Stire.class );
                    if ( stire != null )
                        stiri.add( stire );
                }
            }
        } catch ( Exception e ) {
            Timber.d( e, "Something happened" );
        }
        return stiri;
    }

    @Override
    protected List < VotAnonim > getVoturi() {
        List < VotAnonim > voturi = new ArrayList <>();
        Gson gson = new Gson();
        try {
            JsonObject response = api.getAlegeri().execute().body();
            if ( response != null ) {
                for ( String jsonObjectKeys : response.keySet() ) {
                    JsonObject jsonObject = response.getAsJsonObject( jsonObjectKeys );
                    VotAnonim votAnonim = gson.fromJson( jsonObject, VotAnonim.class );
                    if ( votAnonim != null )
                        voturi.add( votAnonim );
                }
            }
        } catch ( Exception e ) {
            Timber.d( e, "Something happened" );
        }
        return voturi;
    }

    @Override
    protected void insertVot( VotAnonim votAnonim ) {
        Call < VotAnonim > call = api.insertVot( votAnonim );
        call.enqueue( new Callback < VotAnonim >() {
            @Override
            public void onResponse( @NotNull Call < VotAnonim > call, @NotNull Response < VotAnonim > response ) {
                Timber.d( "Success inserting vote in firebase db" );
            }

            @Override
            public void onFailure( @NotNull Call < VotAnonim > call, @NotNull Throwable t ) {
                Timber.d( "fail inserting vote in firebase db" );
                InMemoryDataSource inMemoryDataSource = new InMemoryDataSource();
                inMemoryDataSource.addInMemory( votAnonim );
            }
        } );
    }

    private interface RetrofitApi {
        String BASE_URL = "https://votingapp-eb64d-default-rtdb.europe-west1.firebasedatabase.app/";

        @GET ( "Candidat.json" )
        Call < JsonObject > getCandidati();

        @GET ( "Alegere.json" )
        Call < JsonObject > getAlegeri();

        @GET ( "VotAnonim.json" )
        Call < JsonObject > getVoturi();

        @GET ( "Locatie.json" )
        Call < JsonObject > getLocatii();

        @GET ( "Stire.json" )
        Call < JsonObject > getStiri();


        @POST ( "VotAnonim.json" )
        Call < VotAnonim > insertVot( @Body VotAnonim votAnonim );

        static RetrofitApi createApi() {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor( new StethoInterceptor() )
                    .build();

            return new Retrofit.Builder()
                    .baseUrl( BASE_URL )
                    .client( okHttpClient )
                    .addConverterFactory( GsonConverterFactory.create() )
                    .build()
                    .create( RetrofitApi.class );
        }
    }
}
