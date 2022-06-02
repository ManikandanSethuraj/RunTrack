package com.manny.runtrack.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RunClass::class],
version = 1)
@TypeConverters(Convertors::class)
abstract class RunningDatabase : RoomDatabase() {

    abstract fun getRunDAO() : RunDAO

    var instance : RunningDatabase ?= null



}