package com.manny.runtrack.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "running_table")
data class RunClass(


    var img: Bitmap?= null,
    var timestamp: Long = 0L,
    var avgSpeedKMS : Float = 0f,
    var distanceInMeters : Int = 0,
    var timeInMills : Long = 0L,
    var caloriesBurnt : Int = 0
) {

    @PrimaryKey(autoGenerate = true)
    var id : Int ?= null
}