package com.jlopez.pomodoroschedule.bottomnavigation

import com.jlopez.pomodoroschedule.R

sealed class BottomNavigationScreens(var route: String, var Icon: Int, var title: String) {
    object Pomodoro : BottomNavigationScreens(
        "Pomodoro/{taskName}/{priority}/{isNewTask}",
        R.drawable.ic_pomodoro_icon,
        "Pomodoro/{taskName}/{priority}/{isNewTask}")

    object JobList : BottomNavigationScreens("JobList", R.drawable.ic_job_list_icon, "JobList")
    object Stats : BottomNavigationScreens("Stats", R.drawable.ic_stats_icon, "Statistics")
    object Profile : BottomNavigationScreens("Profile", R.drawable.ic_profile_icon, "Profile")
}
