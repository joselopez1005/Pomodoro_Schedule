package com.jlopez.pomodoroeffeciencytracker.taskmanagementscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jlopez.pomodoroschedule.bottomnavigation.BottomNavigationScreens
import com.jlopez.pomodoroschedule.model.TaskEntry
import com.jlopez.pomodoroschedule.pomodoroscreen.CurrentTaskSection
import com.jlopez.pomodoroschedule.ui.theme.BackgroundGray
import com.jlopez.pomodoroschedule.ui.theme.LightContainerGray
import com.jlopez.pomodoroschedule.ui.theme.MainFontColor
import com.jlopez.pomodoroschedule.ui.theme.SelectedBlueColor

@Composable
fun TaskManagementScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Spacer(modifier = Modifier.height(40.dp))
            Box {
                Column {
                    Text(
                        text = "Tasks",
                        color = MainFontColor,
                        fontSize = 45.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    TaskOverviewSection(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Box {
                Column {
                    Text(
                        text = "All Tasks",
                        color = MainFontColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                    AllTasksSections(
                        listOf(
                            TaskEntry(
                                taskName = "Mobile dev",
                                description = "Making app",
                                priority = "High",
                                pomodoroRepetitions = 4,
                                pomodoroIntervals = 25,
                                pomodoroBreakAmount = 5,
                                timeRemaining = 5
                            ),
                            TaskEntry(
                                taskName = "Gaming",
                                description = "Play Nioh",
                                priority = "Medium",
                                pomodoroRepetitions = 4,
                                pomodoroIntervals = 25,
                                pomodoroBreakAmount = 5,
                                timeRemaining = 5
                            ),
                            TaskEntry(
                                taskName = "Gaming",
                                description = "Play Nioh",
                                priority = "Low",
                                pomodoroRepetitions = 4,
                                pomodoroIntervals = 25,
                                pomodoroBreakAmount = 5,
                                timeRemaining = 5
                            ),
                        ),
                        navController = navController,
                        modifier = Modifier.fillMaxWidth()

                    )
                }
            }
            Box {
                Column {
                    Text(
                        text = "Completed",
                        color = MainFontColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                    CompletedTaskSection(
                        listOfCompletedTasks =
                            listOf(
                                TaskEntry(
                                    taskName = "Gaming",
                                    description = "Play Nioh",
                                    priority = "High",
                                    pomodoroRepetitions = 4,
                                    pomodoroIntervals = 25,
                                    pomodoroBreakAmount = 5,
                                    timeRemaining = 5
                                )
                            )
                    )
                }
            }
            AddNewTaskButtonSection(Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun TaskOverviewSection(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LightContainerGray)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.padding(end = 25.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "5.3",
                        color = MainFontColor,
                        fontSize = 45.sp ,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(end = 15.dp)
                    )
                    Text(
                        text = "Estimated\nTime (h)",
                        color = MainFontColor,
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
            Spacer(modifier = Modifier
                .width(2.dp)
                .height(50.dp)
                .background(Color.Gray)

            )
            Box(
                modifier = Modifier.padding(start = 20.dp)
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "4",
                        color = MainFontColor,
                        fontSize = 45.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(end = 15.dp)
                    )
                    Text(
                        text = "Total tasks\nin project",
                        color = MainFontColor,
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }
    }
}

@Composable
fun AllTasksSections(
    listOfTasks: List<TaskEntry>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(listOfTasks){ index, item ->
            CurrentTaskSection(entry = item,
                modifier = Modifier.clickable {
                    var navRoute = BottomNavigationScreens.Pomodoro.route
                    navRoute = navRoute.replace("{taskName}", item.taskName)
                    navRoute = navRoute.replace("{priority}", item.priority)
                    navRoute = navRoute.replace("{isNewTask}", "true")
                    navController.navigate(
                        navRoute
                    )
                })
        }
    }
}

@Composable
fun CompletedTaskSection(
    listOfCompletedTasks: List<TaskEntry>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(listOfCompletedTasks){ index, item ->
            CurrentTaskSection(entry = item)
        }
    }
}

@Composable
fun AddNewTaskButtonSection(
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
           backgroundColor = SelectedBlueColor
        ),
        modifier = modifier
            .padding(horizontal = 25.dp)
            .clip(RoundedCornerShape(10.dp))
    ){
        Text(
            text = "+ Add new task",
            color = MainFontColor,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp)
        )
    }
}


@Preview
@Composable
fun TaskOverviewSectionPreview() {
    TaskOverviewSection(modifier = Modifier.fillMaxWidth())
}