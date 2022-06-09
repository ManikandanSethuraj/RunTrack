package com.manny.runtrack.di

import android.content.Context
import androidx.room.Room
import com.manny.runtrack.db.RunningDatabase
import com.manny.runtrack.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The ApplicationComponent::class in was usually created by developer in old Dagger2.
 * This class used to determine when the objects in the module is created and destroyed.
 * The @InstallIn determines that the Module has to be installed in the ApplicationComponent class.
 *
 * *** @InstallIn doesn't make it Singleton
 *
 * All of the dependencies which we create in our AppModule will be created in the onCreate
 * function of our Application.
 * That means they exist in whole lifetime of our App.
 *
 * Like "ApplicationComponent" there is "ActivityComponent" means the lifeCycle of the AppModule
 * and dependencies mentioned will be active for the lifecycle of the Activity.
 * Likewise there "FragmentComponent", "ServiceComponent"
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * this @Provides
     */

    @Singleton
    @Provides
     fun provideRunningDataBase(
        @ApplicationContext app : Context
     ) = Room.databaseBuilder(
         app,
         RunningDatabase::class.java,
         Constants.RUNNING_DATABASE_NAME
     ).build()


    @Singleton
    @Provides
    fun provideRunDAO(db : RunningDatabase) = db.getRunDAO()

}