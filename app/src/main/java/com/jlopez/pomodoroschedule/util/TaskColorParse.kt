package com.jlopez.pomodoroschedule.util

import androidx.compose.ui.graphics.Color
import com.jlopez.pomodoroschedule.model.TaskEntry
import com.jlopez.pomodoroschedule.ui.theme.*
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
        "medium" -> MediumTaskColorBorder
        "low" -> LowTaskColorBorder
        else -> Color.Black
    }
}

fun parseTaskColor(priority: String): Color {
    return when(priority) {
        "high" -> HighTaskRed
        "medium" -> MediumTaskColor
        "low" -> LowTaskColor
        else -> Color.Black
    }
}

