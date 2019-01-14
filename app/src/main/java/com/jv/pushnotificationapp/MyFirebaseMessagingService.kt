package com.jv.pushnotificationapp

import android.annotation.SuppressLint
import android.app.Notification
import android.util.Log
import android.app.NotificationManager
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {
    //Tag para buscar el mensaje
    val TAG = "FirebaseMessagingService"

    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //muestra el mensaje
        Log.d(TAG, "Mensaje: ${remoteMessage.from}")
        showNotificationLocal(remoteMessage)
    }

    //metodo que muestra la notificacion con la aplicacion abierta
    private fun showNotificationLocal(remoteMessage: RemoteMessage){ //pasa el titulo y el cuerpo del mensaje por parametro
        //id de la notificacion
        val mNotificationID = 101
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(mNotificationID, notificationIntent(remoteMessage))

    }
    //metodo para configurar el canal de la notificacion
    private fun defaultNotification(remoteMessage: RemoteMessage) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Notification.Builder(this, NotificationUtils.CHANNEL_ID)
    }
    else {
        Notification.Builder(this)
        //muestra el contenido del mensaje, titulo y cuerpo
    }.apply {
        setContentTitle(remoteMessage.notification?.title)
        setContentText(remoteMessage.notification?.body)
        setAutoCancel(true) //click en el mensaje para cerrarlo
        setSmallIcon(android.R.drawable.ic_dialog_info) //icon del mensaje
    }
    private fun notificationIntent(remoteMessage: RemoteMessage) = PendingIntent.getActivity(this,
        0,
        //pasa los metodos al main
        Intent(this, MainActivity::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT).run {
        defaultNotification(remoteMessage).setContentIntent(this).build()
    }
}