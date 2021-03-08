package examplesAll.servicesExamples.ServiceClasses

import android.R
import android.app.*
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import examplesAll.servicesExamples.activityAll.ForegroundServiceExample
import java.util.*


class MyForegroundSevice : IntentService {
    private var mRandomNumber = 0
    private var mIsRandomGeneratorOn = false
    var TAG : String = "MyForegroundSevice"
    private val notificationManagerCompat: NotificationManagerCompat? = null
    private var notificationManager: NotificationManager? = null
    private val mediaSession: MediaSessionCompat? = null

    private val MIN = 0
    private val MAX = 100

    constructor() : super(null) {}

    override fun onHandleIntent(intent: Intent?) {
        startForeground(1, getNotification())
        mIsRandomGeneratorOn =true;
        startRandomNumberGenerator();
    }

      private fun getNotification(): Notification? {
        val NOTIFICATION_CHANNEL_ID = "123"
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "DemoAll", NotificationManager.IMPORTANCE_DEFAULT)
            // Configure the notification channel.
            notificationChannel.description = "DemoAll"
            notificationManager!!.createNotificationChannel(notificationChannel)
        }

        val intent = Intent(applicationContext, ForegroundServiceExample::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 1, intent, 0)

        val builder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.btn_star)
                .setLargeIcon(BitmapFactory.decodeResource(applicationContext.getResources(), R.drawable.btn_star))
                .addAction(R.drawable.ic_media_previous, "previous", playbackAction(3))
                .addAction(R.drawable.ic_media_pause, "pause", playbackAction(1))
                .addAction(R.drawable.ic_media_next, "next", playbackAction(2))
                .setContentTitle("ForegroundApp")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setChannelId("123")
                .setAutoCancel(true)
        return builder.build()
    }

    private fun playbackAction(actionNumber: Int): PendingIntent? {
        val playbackAction = Intent(this, MyForegroundSevice::class.java)
        when (actionNumber) {
            0 -> {
                // Play
                playbackAction.action = "MediaPlayerService.ACTION_PLAY"
                return PendingIntent.getService(this, actionNumber, playbackAction, 0)
            }
            1 -> {
                // Pause
                playbackAction.action = "MediaPlayerService.ACTION_PAUSE"
                return PendingIntent.getService(this, actionNumber, playbackAction, 0)
            }
            2 -> {
                // Next track
                playbackAction.action = "MediaPlayerService.ACTION_NEXT"
                return PendingIntent.getService(this, actionNumber, playbackAction, 0)
            }
            3 -> {
                // Previous track
                playbackAction.action = "MediaPlayerService.ACTION_PREVIOUS"
                return PendingIntent.getService(this, actionNumber, playbackAction, 0)
            }
            else -> {
            }
        }
        return null
    }


    private fun startRandomNumberGenerator() {
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(1000)
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = Random().nextInt(MAX) + MIN
                    Log.e(TAG, "Thread id: " + Thread.currentThread().id + ", Random Number: " + mRandomNumber)
                }
            } catch (e: InterruptedException) {
                Log.e(TAG, "Thread Interrupted")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mIsRandomGeneratorOn = false
        Log.e(TAG, "STOP_SERVICE" + ", thread Id: " + Thread.currentThread().getId());
    }



}