package com.jv.pushnotificationapp
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class NotificationUtils(base: Context): ContextWrapper(base) {
    //creacion del canal
    companion object {
        const val CHANNEL_ID = "com.jv.pushnotificationapp.CHANNEL_ID"
        const val CHANNEL_NAME = "Show notifications UTAD"
    }


    private val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createChannels()
    }

    //elimina el canal
    fun deleteNotificationChannel() {
        mNotificationManager.deleteNotificationChannel(CHANNEL_ID)
    }
    //metodo para crear el canal
    private fun createChannels() {
        NotificationChannel(CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT).apply {
            enableLights(true)
            enableVibration(true)
            importance = NotificationManager.IMPORTANCE_LOW
            lightColor = Color.GREEN
            lockscreenVisibility = Notification.VISIBILITY_PRIVATE

            mNotificationManager.createNotificationChannel(this)
        }
    }
}