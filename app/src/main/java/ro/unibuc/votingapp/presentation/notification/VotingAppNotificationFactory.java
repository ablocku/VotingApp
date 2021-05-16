package ro.unibuc.votingapp.presentation.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import ro.unibuc.votingapp.R;
import ro.unibuc.votingapp.presentation.VotingAppService;
import ro.unibuc.votingapp.presentation.view.MainActivity;


public final class VotingAppNotificationFactory {
    private static final int BASE_ID = 1;
    private static final int SERVICE_NOTIFICATION_ID = BASE_ID + 1;
    private static final int HELLO_NOTIFICATION_ID = SERVICE_NOTIFICATION_ID + 1;
    private static final int NOTIFICATION_LAUNCH_CODE = 485;

    public static int getServiceNotificationId() {
        return SERVICE_NOTIFICATION_ID;
    }

    public static int getHelloNotificationId() {
        return HELLO_NOTIFICATION_ID;
    }

    public static Notification createProcessingWorkNotification( Context context ) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( context, VotingAppNotificationChannelFactory.CHANNEL_ID )
                .setSmallIcon( android.R.drawable.ic_popup_sync )
                .setCategory( NotificationCompat.CATEGORY_SERVICE )
                .setPriority( Notification.PRIORITY_MAX )
                .setContentTitle( context.getString( R.string.work ) )
                .setContentText( context.getString( R.string.processing ) )
                .setAutoCancel( true )
                .setContentIntent( createContentIntent( context ) )
                .addAction( createStopAction( context ) );

        return builder.build();
    }

    public static Notification createHelloNotification( Context context ) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( context, VotingAppNotificationChannelFactory.CHANNEL_ID )
                .setSmallIcon( android.R.drawable.star_on )
                .setCategory( NotificationCompat.CATEGORY_STATUS )
                .setPriority( Notification.PRIORITY_DEFAULT )
                .setContentTitle( context.getString( R.string.hello ) )
                .setContentText( context.getString( R.string.saidHello ) )
                .setAutoCancel( true )
                .setContentIntent( createContentIntent( context ) );

        return builder.build();
    }

    public static Notification createCustomHelloNotification( Context context, String s, String s2 ) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( context, VotingAppNotificationChannelFactory.CHANNEL_ID )
                .setSmallIcon( android.R.drawable.star_on )
                .setCategory( NotificationCompat.CATEGORY_STATUS )
                .setPriority( Notification.PRIORITY_DEFAULT )
                .setContentTitle( s )
                .setContentText( s2 )
                .setAutoCancel( true )
                .setContentIntent( createContentIntent( context ) );

        return builder.build();
    }

    private static PendingIntent createContentIntent( Context context ) {
        Intent intent = new Intent( context, MainActivity.class );

        return PendingIntent.getActivity( context,
                NOTIFICATION_LAUNCH_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }

    private static NotificationCompat.Action createStopAction( Context context ) {
        return new NotificationCompat.Action.Builder( android.R.drawable.ic_media_pause,
                "Stop",
                createStopActionPendingIntent( context ) )
                .build();
    }

    private static PendingIntent createStopActionPendingIntent( Context context ) {
        Intent intent = new Intent( context, VotingAppService.class );
        intent.putExtra( VotingAppService.TYPE_KEY, VotingAppService.TYPE_FINISH );

        return PendingIntent.getService( context,
                VotingAppService.TYPE_FINISH,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
    }
}
