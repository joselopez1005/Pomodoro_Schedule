package com.jlopez.pomodoroschedule.util

import androidx.compose.ui.graphics.Color
import com.jlopez.pomodoroschedule.model.TaskEntry
import com.jlopez.pomodoroschedule.ui.theme.HighTaskRed
import com.jlopez.pomodoroschedule.ui.theme.HighTaskRedBorder
import com.jlopez.pomodoroschedule.ui.theme.LowTaskColor
import com.jlopez.pomodoroschedule.ui.theme.MediumTaskColor
import java.util.*

fun parseTaskColor(entry: TaskEntry): Color {
    return when(entry.priority.lowercase(Locale.ROOT)) {
        "high" -> HighTaskRed
        "medium" -> MediumTaskColor
        "low" -> LowTaskColor
        else -> Color.Black
    }
}

fun parseTaskColorBorder(entry: TaskEntry): Color {
    return when(entry.priority.lowercase(Locale.ROOT)) {
        "high" -> HighTaskRedBorder
        "medium" -> MediumTaskColor
        "low" -> LowTaskColor
        else -> Color.Black
    }
}