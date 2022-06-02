package com.manny.runtrack.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.manny.runtrack.db.RunDAO
import com.manny.runtrack.repositories.MainRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class StatisticsViewModel @ViewModelInject constructor(
    val repository : MainRepository
) : ViewModel() {

}