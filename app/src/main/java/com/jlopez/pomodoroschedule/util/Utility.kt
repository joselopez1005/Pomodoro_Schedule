package com.jlopez.pomodoroschedule.util

import java.util.concurrent.TimeUnit

object Utility {

    private const val TIME_FORMAT = "%02d:%02d"

    fun formatTime(time: Long): String {
        return String.format(
            TIME_FORMAT,
            TimeUnit.MILLISECONDS.toMinutes(time),
            TimeUnit.MILLISECONDS.toSeconds(time) % 60
        )
    }



}