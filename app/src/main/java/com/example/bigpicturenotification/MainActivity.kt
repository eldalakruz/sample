package com.example.bigpicturenotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    val CHANNEL_ID ="Android tutorial"
    val CHANNE_NAME ="Android_notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_send).setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationBuilder: Notification.Builder? = null
                val importance = NotificationManager.IMPORTANCE_HIGH
                val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNE_NAME, importance)
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.image)
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent = PendingIntent.getActivity(
                    this, Calendar.getInstance().timeInMillis.toInt(),
                    intent, 0
                )
                notificationBuilder = Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("I LOVE YOU...")
                    .setContentText("I LOVE YOU...")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setStyle(Notification.BigPictureStyle(notificationBuilder).bigPicture(bitmap))
                    .addAction(R.drawable.image, "show activity", pendingIntent)
                val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager
                notificationManager.createNotificationChannel(notificationChannel)
                notificationManager.notify(0, notificationBuilder.build())

            }
            else {
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.image)
                val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager
                val bigPictureNotification = NotificationCompat.BigPictureStyle()
                bigPictureNotification.bigPicture(bitmap).build()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                val pendingIntent = PendingIntent.getActivity(
                    this, Calendar.getInstance().timeInMillis.toInt(),
                    intent, 0)

              val  notificationBuilder = NotificationCompat.Builder(this,)
                    .setContentTitle("I LOVE YOU...")
                    .setContentText("I LOVE YOU...")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setStyle(bigPictureNotification)
                    .addAction(R.drawable.image, "show activity", pendingIntent)

                notificationManager.notify(0,notificationBuilder.build())






            }


        }}
}