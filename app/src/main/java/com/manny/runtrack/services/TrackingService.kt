package com.manny.runtrack.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.manny.runtrack.R
import com.manny.runtrack.ui.MainActivity
import com.manny.runtrack.util.Constants
import com.manny.runtrack.util.Constants.ACTION_PAUSE_SERVICE
import com.manny.runtrack.util.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.manny.runtrack.util.Constants.ACTION_START_OR_RESUME_SERVICE
import com.manny.runtrack.util.Constants.ACTION_STOP_SERVICE
import com.manny.runtrack.util.Constants.NOTIFICATION_CHANNEL_ID
import com.manny.runtrack.util.Constants.NOTIFICATION_CHANNEL_NAME
import com.manny.runtrack.util.Constants.NOTIFICATION_ID
import timber.log.Timber

/**
 * The reason behind extending LifeCycleService instead of Service the Class, we need to Observer LiveData inside this Class
 *  An also TrackingService called outside of this class from LiveData needs to follow LifeCycle
 *
 */


/**
 *  Service is of following types: Foreground , background , bound
 *  Background is cancelled when the app is killed.
 *  Foreground still runs when the app is Killed.
 *
 */
/**
 * A pending Intent has to be introduced to open the App on the click of notification in notification toolbar
 */

class TrackingService : LifecycleService() {

    var isFirstRun = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.apply {
            when(this.action){
                ACTION_START_OR_RESUME_SERVICE ->
                {
                    if (isFirstRun){
                        startForegroundService()
                        isFirstRun = false
                    }
                    Timber.d("Start")
                }
                ACTION_STOP_SERVICE -> Timber.d("Stop")
                ACTION_PAUSE_SERVICE -> Timber.d("Pause")
            }
        }

//        when(intent?.action){
//            ACTION_START_OR_RESUME_SERVICE -> Timber.d("Start")
//            ACTION_STOP_SERVICE -> Timber.d("Stop")
//            ACTION_PAUSE_SERVICE -> Timber.d("Pause")
//        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegroundService(){

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                                 as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_run)
            .setContentTitle("Running App")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())

    }

    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this,MainActivity::class.java).also {
            it.action = ACTION_SHOW_TRACKING_FRAGMENT
        },
        FLAG_UPDATE_CURRENT

    )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager : NotificationManager){

        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(notificationChannel)
    }
}