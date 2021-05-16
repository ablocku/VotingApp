package ro.unibuc.votingapp.presentation;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ro.unibuc.votingapp.R;
import ro.unibuc.votingapp.presentation.notification.VotingAppNotificationFactory;
import timber.log.Timber;

public final class VotingAppReplacedBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive( Context context, Intent intent ) {
        if ( Intent.ACTION_MY_PACKAGE_REPLACED.equals( intent.getAction() ) ) {
            Timber.d( "broadcast received for my package replace" );
            //ne initializam sistemul de notificari
            NotificationManager notificationManager = ( NotificationManager ) context.getSystemService( Context.NOTIFICATION_SERVICE );
            //afisam o notificare de bun venit
            notificationManager.notify( VotingAppNotificationFactory.getHelloNotificationId(),
                    VotingAppNotificationFactory.createCustomHelloNotification( context,
                            context.getString( R.string.broadcast_s1 ), context.getString( R.string.broadcast_s2 ) ) );
        }
    }
}
