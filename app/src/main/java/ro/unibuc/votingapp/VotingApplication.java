package ro.unibuc.votingapp;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ro.unibuc.votingapp.presentation.notification.VotingAppNotificationChannelFactory;
import timber.log.Timber;

public final class VotingApplication extends Application {
    private static VotingApplication votingApplication;
    private final List < Activity > activities;
    private NotificationManager notificationManager = null;

    public VotingApplication() {
        super();
        votingApplication = this;
        activities = new ArrayList <>();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setupLibs();

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            notificationManager = ( NotificationManager ) getSystemService( Context.NOTIFICATION_SERVICE );
            notificationManager.createNotificationChannel( VotingAppNotificationChannelFactory.createProcessingWorkNotificationChannel() );
        }
    }

    private void setupLibs() {
        Stetho.initializeWithDefaults( this );

        if ( !BuildConfig.my_flag ) {
            Timber.plant( new Timber.DebugTree() );
        } else {
            Timber.plant( new Timber.Tree() {
                @Override
                protected void log( int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t ) {
                    if ( priority < Log.INFO ) {
                        return;
                    }

                    if ( t != null ) {
                        FirebaseCrashlytics.getInstance().recordException( t );
                    }

                    String crashlyticsMessage = String.format( "[%s] %s", tag, message );
                    FirebaseCrashlytics.getInstance().log( crashlyticsMessage );
                }
            } );
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if ( !activities.isEmpty() )
            Timber.wtf( "aplicatia inca are activitati, desi a fost terminata" );
        else
            Timber.d( "Aplicatia -> onTerminate() nu mai are activitati" );
    }

    public void addActivity( Activity a ) {
        votingApplication.activities.add( a );
    }

    public void removeActivity( Activity a ) {
        votingApplication.activities.remove( a );
    }

    public static VotingApplication getApplication() {
        return votingApplication;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }
}
