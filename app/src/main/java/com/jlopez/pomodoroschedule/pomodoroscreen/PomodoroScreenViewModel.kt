package com.jlopez.pomodoroschedule.pomodoroscreen

import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlopez.pomodoroschedule.model.TaskEntry
import com.jlopez.pomodoroschedule.util.Utility
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PomodoroScreenViewModel: ViewModel() {

    //TODO: Add variable for total time
    private val _currentPercentage = MutableLiveData(1f)
    val currentPercentage: LiveData<Float> = _currentPercentage

    private val _currentTime = MutableLiveData(1500L * 1000L)
    val currentTime: LiveData<Long> =  _currentTime

    private val _isTimerRunning = MutableLiveData(false)
    val isTimerRunning: LiveData<Boolean> = _isTimerRunning

    private val _timeInText = MutableLiveData("25:00")
    val timeInText: LiveData<String> = _timeInText

    private var taskEntry= TaskEntry(
        taskName = "Default name",
        description = "default description",
        priority = "Low",
        pomodoroRepetitions = 4,
        pomodoroIntervals = 25,
        pomodoroBreakAmount = 5,
        timeRemaining = 30
    )

    fun handlePomodoroTimer() {
        if(_isTimerRunning.value!!) {
            pauseTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer(){
        _isTimerRunning.value = true
        viewModelScope.launch {
            while(_currentTime.value!! > 0 && _isTimerRunning.value!!) {
                delay(100L)
                _currentTime.value = _currentTime.value!! - 100L
                _currentPercentage.value = _currentTime.value!! / (1500L * 1000L).toFloat()
                _timeInText.value = Utility.formatTime(_currentTime.value!!)
            }
        }
    }

    private fun pauseTimer() {
        if(_currentTime.value!! <= 0L) {
            _currentTime.value = 1500L * 1000L
            _isTimerRunning.value = true
        } else {
            _isTimerRunning.value = false

        }
    }

    fun createTaskEntry(taskName: String, priority: String, isNewTask: Boolean): TaskEntry {
        return if(isNewTask) {
            taskEntry = TaskEntry(
                taskName = taskName,
                priority = priority,
                description = "Test",
                pomodoroRepetitions = 4,
                pomodoroIntervals = 15,
                pomodoroBreakAmount = 5,
                timeRemaining = 30
            )
            taskEntry
        } else {
            taskEntry
        }
    }
}