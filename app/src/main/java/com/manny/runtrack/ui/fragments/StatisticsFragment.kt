package com.manny.runtrack.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manny.runtrack.R
import com.manny.runtrack.ui.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val statisticsViewModel : StatisticsViewModel by viewModels()
     
}