package com.s26462.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class CreateNotification : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        super.onCreate()
//        setContentView(R.layout.activity_main)
        createNotificationChannel()
        val requestCode = 1
        var id = 0

        Toast.makeText(this, "NotificationAppDL", Toast.LENGTH_SHORT).show()

        val intent1 = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,requestCode,intent1, PendingIntent.FLAG_MUTABLE)

        val notification = NotificationCompat.Builder(this,
            getString(R.string.channelID))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_text))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(id++, notification)

        return null
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return
        // SDK >= 26
        val notificationChannel = NotificationChannel(
            getString(R.string.channelID),
            getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationChannel.description =
            getString(R.string.channel_description)
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannel(notificationChannel)
    }

//    override fun onBind(intent: Intent?): IBinder? {
//        TODO("Not yet implemented")
//    }
}