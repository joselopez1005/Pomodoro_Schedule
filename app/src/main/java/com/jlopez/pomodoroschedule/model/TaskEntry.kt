package com.jlopez.pomodoroschedule.model

data class TaskEntry (
    val taskName: String,
    val description: String,
    val priority: String,
    val pomodoroRepetitions: Int,
    val pomodoroIntervals: Int,
    val pomodoroBreakAmount: Int
)
