package com.example.admin.appclean.utils.NotificationUtil;


import com.example.admin.appclean.R;
import com.example.admin.appclean.presentation.ui.activities.SplashScreenActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.content.Intent;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;


public class FirebaseMService extends FirebaseMessagingService {

    private String mensajeNotificacion = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        mensajeNotificacion = remoteMessage.getData().toString();
        System.out.println("mensajeNotificacion:  " + mensajeNotificacion);
    }


    private void sendNotification(String title, String message) {

        Intent intent = new Intent(this, SplashScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);


        NotificationCompat.Builder notificationBuilde = new NotificationCompat.Builder(this)
                .setSmallIcon(getNotificationIcon())
                .setContentTitle(title).setContentText(message).setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(2))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilde.setContentIntent(pendingIntent);
        notificationManager.notify(0, notificationBuilde.build());
    }

    //Definir el icono a mostrar segun la version del dispositivo
    private int getNotificationIcon() {
        return Build.VERSION.SDK_INT >=  Build.VERSION_CODES.LOLLIPOP ? R.mipmap.ic_launcher : R.mipmap.ic_launcher;
    }
}
