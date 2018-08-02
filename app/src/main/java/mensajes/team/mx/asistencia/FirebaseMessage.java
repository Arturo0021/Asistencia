package mensajes.team.mx.asistencia;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessage extends FirebaseMessagingService {

    String From = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        From = remoteMessage.getFrom();
        if(remoteMessage.getNotification() != null) {
            Log.i("#TAG", remoteMessage.getNotification().getBody());
        }

        if(remoteMessage.getData().size() > 0) {
            Log.d("#TAG ","DATA: " + remoteMessage.getData());
        }

        show_Notification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

    }

    private void show_Notification(String Notificacion, String Body) {

        Intent intent = new Intent(this, Main_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pending = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notibuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.guerrero_dan)
                .setContentTitle(Notificacion)
                .setContentText(Body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pending);

        NotificationManager notify = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notify.notify(0, notibuilder.build());

    }


}
