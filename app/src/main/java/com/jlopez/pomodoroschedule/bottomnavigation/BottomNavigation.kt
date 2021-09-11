package com.jlopez.pomodoroschedule.bottomnavigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import com.jlopez.pomodoroschedule.ui.theme.LightContainerGray
import com.jlopez.pomodoroschedule.ui.theme.SelectedBlueColor
import com.jlopez.pomodoroschedule.ui.theme.UnselectedGrayColor

@Composable
fun BottomNavigationBar() {
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
        items.forEach{ item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.Icon), contentDescription = item.title) },
                selectedContentColor = SelectedBlueColor ,
                unselectedContentColor = UnselectedGrayColor,
                alwaysShowLabel = false,
                selected = item.title == "Pomodoro",
                onClick = {

                }
            )

        }
    }
}