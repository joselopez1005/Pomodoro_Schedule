package com.jlopez.pomodoroschedule.bottomnavigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navArgument
import com.jlopez.pomodoroeffeciencytracker.taskmanagementscreen.TaskManagementScreen
import com.jlopez.pomodoroschedule.model.TaskEntry
import com.jlopez.pomodoroschedule.pomodoroscreen.PomodoroScreen
import com.jlopez.pomodoroschedule.ui.theme.LightContainerGray
import com.jlopez.pomodoroschedule.ui.theme.SelectedBlueColor
import com.jlopez.pomodoroschedule.ui.theme.UnselectedGrayColor

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavigationScreens.Pomodoro,
        BottomNavigationScreens.JobList,
        BottomNavigationScreens.Stats,
        BottomNavigationScreens.Profile
    )

    var isSelected by remember {
        mutableStateOf(false)
    }
    BottomNavigation(
        backgroundColor = LightContainerGray,
        contentColor = LightContainerGray
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach{ item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.Icon), contentDescription = item.title) },
                selectedContentColor = SelectedBlueColor ,
                unselectedContentColor = UnselectedGrayColor,
                alwaysShowLabel = false,
                selected = item.title == currentRoute,
                onClick = {
                    var navRoute = item.route
                    navRoute = navRoute.replace("{isNewTask}", "false")
                    navController.navigate(navRoute) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

        }
    }
}



@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavigationScreens.Pomodoro.route){
        composable(BottomNavigationScreens.Pomodoro.route,
            arguments = listOf(
                navArgument("taskName") {
                    type = NavType.StringType
                },
                navArgument("priority") {
                    type = NavType.StringType
                },
                navArgument("isNewTask") {
                    type = NavType.BoolType
                }
            )
        ) {
            val taskName = remember{
                it.arguments?.getString("taskName") ?: "Default Name"
            }
            val priority = remember {
                it.arguments?.getString("priority") ?: "Low"
            }
            val isNewTask = remember{
                it.arguments?.getBoolean("isNewTask") ?: true
            }
            PomodoroScreen(taskName,priority, isNewTask, navController)
        }
        composable(BottomNavigationScreens.JobList.route) {
            TaskManagementScreen(navController)
        }
    }
}