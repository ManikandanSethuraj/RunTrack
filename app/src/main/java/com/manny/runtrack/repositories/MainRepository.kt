package com.manny.runtrack.repositories

import androidx.lifecycle.LiveData
import com.manny.runtrack.db.RunClass
import com.manny.runtrack.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO : RunDAO
) {



    suspend fun addRun(run : RunClass) = runDAO.insert(run)
    suspend fun deleteRun(run : RunClass) = runDAO.delete(run)


    fun getAllRunsSortedByDate() : LiveData<List<RunClass>> = runDAO.getAllRunsSortedByDate()
    fun getAllRunsSortedByAvgSpeed() : LiveData<List<RunClass>> = runDAO.getAllRunsSortedByAvgSpeed()
    fun getAllRunsSortedByAvgDistance() : LiveData<List<RunClass>> = runDAO.getAllRunsSortedByAvgDistance()
    fun getAllRunsSortedByAvgTime() : LiveData<List<RunClass>> = runDAO.getAllRunsSortedByAvgTime()
    fun getAllRunsSortedByAvgCalories() : LiveData<List<RunClass>> = runDAO.getAllRunsSortedByAvgCalories()

    fun getAvgSpeed() = runDAO.getAvgSpeed()
    fun getTotalTimeInMills() = runDAO.getTotalTimeInMills()
    fun getTotalDistance() = runDAO.getTotalDistance()
    fun getTotalCalories() = runDAO.getTotalDistance()


}