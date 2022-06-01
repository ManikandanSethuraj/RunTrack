package com.manny.runtrack.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RunDAO {


    @Insert(onConflict = REPLACE)
    suspend fun insert(runClass: RunClass) : Long

    @Delete()
    suspend fun delete(runClass: RunClass)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate() : LiveData<List<RunClass>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeedKMS DESC")
    fun getAllRunsSortedByAvgSpeed() : LiveData<List<RunClass>>

    @Query("SELECT * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByAvgDistance() : LiveData<List<RunClass>>

    @Query("SELECT * FROM running_table ORDER BY timeInMills DESC")
    fun getAllRunsSortedByAvgTime() : LiveData<List<RunClass>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurnt DESC")
    fun getAllRunsSortedByAvgCalories() : LiveData<List<RunClass>>

    @Query("SELECT SUM(timeInMills) FROM running_table")
    fun getTotalTimeInMills() : LiveData<Long>

    @Query("SELECT SUM(distanceInMeters) FROM running_table")
    fun getTotalDistance() : LiveData<Int>

    @Query("SELECT AVG(avgSpeedKMS) FROM running_table")
    fun getAvgSpeed() : LiveData<Float>

    @Query("SELECT SUM(caloriesBurnt) FROM running_table")
    fun getTotalCaloriesBurnt() : LiveData<Int>


}