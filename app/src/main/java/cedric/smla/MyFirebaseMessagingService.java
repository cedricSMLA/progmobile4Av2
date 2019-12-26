package cedric.smla;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CANAL = "MyNotifCanal";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // on recupere le message
        //remoteMessage c l'élément qu'on va recup
        //getNotif contient l'ensemble des informations
        //getbody permet de recup le contenu de la notification
        String myMessage = remoteMessage.getNotification().getBody();

        //on affiche ce message en console - debug
        //Log.d("FirebaseMessage", "Message reçu : " + myMessage);

        //action
        //rediriger vers une page  web
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.fr"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        // creer une notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CANAL);
        notificationBuilder.setContentTitle("Eh ouais mon gars !");
        notificationBuilder.setContentText(myMessage);

        //ajouter l'action
        notificationBuilder.setContentIntent(pendingIntent);

        //une icone
        notificationBuilder.setSmallIcon(R.drawable.chevron);

        //envoyer la notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = getString(R.string.notification_channel_id);
            String channelTitle = getString(R.string.notification_channel_title);
            String channelDescription = getString(R.string.notification_channel_desc);
            NotificationChannel channel = new NotificationChannel(channelId, channelTitle, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(channelId);
        }

    }
}