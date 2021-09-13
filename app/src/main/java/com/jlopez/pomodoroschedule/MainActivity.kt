package com.jlopez.pomodoroschedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jlopez.pomodoroeffeciencytracker.taskmanagementscreen.TaskManagementScreen
import com.jlopez.pomodoroschedule.bottomnavigation.BottomNavigationBar
import com.jlopez.pomodoroschedule.pomodoroscreen.PomodoroScreen
import com.jlopez.pomodoroschedule.ui.theme.PomodoroScheduleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PomodoroScheduleTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar()}
                ) {
                    TaskManagementScreen()

                }
            }
        }
    }
}

