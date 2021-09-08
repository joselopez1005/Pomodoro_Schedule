package com.jlopez.pomodoroschedule.pomodoroscreen

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jlopez.pomodoroschedule.util.Utility
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PomodoroScreenViewModel: ViewModel() {

    private var countDownTimer: CountDownTimer? = null

//    private var _currentPercentage = MutableLiveData(1f)
//    val currentPercentage: LiveData<Float> = _currentPercentage
//
//    private var _currentTime = MutableLiveData(1500L * 1000L)
//    val currentTime: LiveData<Long> = _currentTime
//
//    private var _isTimerRunning = MutableLiveData(false)
//    val isTimerRunning: LiveData<Boolean> = _isTimerRunning

    private val _currentPercentage = MutableStateFlow(1f)
    val currentPercentage = _currentPercentage.asStateFlow()

    private val _currentTime = MutableStateFlow(1500L * 1000L)
    val currentTime = _currentTime.asStateFlow()

    private val _isTimerRunning = MutableStateFlow(false)
    val isTimerRunning = _isTimerRunning.asStateFlow()


}