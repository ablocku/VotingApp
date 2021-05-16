package ro.unibuc.votingapp.presentation.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

public final class VotingAppNotificationChannelFactory {

    public static final String CHANNEL_ID = "channel-id";

    @RequiresApi ( api = Build.VERSION_CODES.O )
    public static NotificationChannel createProcessingWorkNotificationChannel() {
        NotificationChannel channel = new NotificationChannel( CHANNEL_ID,
                "my processing channel 2",
                NotificationManager.IMPORTANCE_HIGH );
        channel.setShowBadge( true );
        channel.enableVibration( true );
        return channel;
    }
}
