package com.manny.runtrack.services

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.manny.runtrack.util.Constants
import com.manny.runtrack.util.Constants.ACTION_PAUSE_SERVICE
import com.manny.runtrack.util.Constants.ACTION_START_OR_RESUME_SERVICE
import com.manny.runtrack.util.Constants.ACTION_STOP_SERVICE
import timber.log.Timber

/**
 * The reason behind extending LifeCycleService instead of Service the Class, we need to Observer LiveData inside this Class
 *  An also TrackingService called outside of this class from LiveData needs to follow LifeCycle
 *
 */

class TrackingService : LifecycleService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.apply {
            when(this.action){
                ACTION_START_OR_RESUME_SERVICE -> Timber.d("Start")
                ACTION_STOP_SERVICE -> Timber.d("Stop")
                ACTION_PAUSE_SERVICE -> Timber.d("Pause")
            }
        }

        when(intent?.action){
            ACTION_START_OR_RESUME_SERVICE -> Timber.d("Start")
            ACTION_STOP_SERVICE -> Timber.d("Stop")
            ACTION_PAUSE_SERVICE -> Timber.d("Pause")
        }

        return super.onStartCommand(intent, flags, startId)
    }
}