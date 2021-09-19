package com.jlopez.pomodoroschedule.addtaskscreem

import android.widget.RadioGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jlopez.pomodoroschedule.ui.theme.LightContainerGray
import com.jlopez.pomodoroschedule.ui.theme.MainFontColor
import com.jlopez.pomodoroschedule.ui.theme.SelectedBlueColor
import com.jlopez.pomodoroschedule.ui.theme.UnselectedGrayColor
import com.jlopez.pomodoroschedule.util.Utility
import com.jlopez.pomodoroschedule.util.parseTaskColor

@Composable
fun AddTaskScreen() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            Spacer(modifier = Modifier.height(40.dp))
            Box{
               Column {
                   Text(
                       text = "New Task",
                       color = MainFontColor,
                       fontSize = 45.sp,
                       fontWeight = FontWeight.Bold,
                       modifier = Modifier.padding(horizontal = 20.dp)
                   )
                   Spacer(modifier = Modifier.height(30.dp))
                   AddTaskNameDetailSection(Modifier.fillMaxWidth())
               }
            }
            Box{
                Column {
                    Text(
                        text = "Task Priority",
                        color = MainFontColor,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                    TaskPrioritySection(
                        priorityList = listOf("high", "medium", "low"),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }
            }
            TaskOptionSection(Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun AddTaskNameDetailSection(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LightContainerGray)
    ) {
        Column{
            InputTextField(request = "Task Name", modifier = modifier.fillMaxWidth())
            InputTextField(request = "Short Description", modifier = modifier.fillMaxWidth())
        }
    }
}

@Composable
fun InputTextField(
    request: String,
    modifier: Modifier = Modifier
) {
    var text by remember{
        mutableStateOf("")
    }

    TextField(
        value = text,
        onValueChange = {text = it},
        label = {
            Text(
                text = request,
                color = MainFontColor
            )
        },
        modifier = modifier
    )
}

@Composable
fun TaskPrioritySection(
    priorityList: List<String>,
    modifier: Modifier = Modifier
) {
    var selectedPriority by remember {
        mutableStateOf(0)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LightContainerGray)
    ){
        LazyRow(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(priorityList.size) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(height = 50.dp, width = 120.dp)
                        .padding(horizontal = 10.dp)
                        .clickable { selectedPriority = it }
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (selectedPriority == it) {
                                parseTaskColor(priorityList[it])
                            } else {
                                UnselectedGrayColor
                            }
                        )
                        .border(3.dp, parseTaskColor(priorityList[it]))
                        .padding(5.dp)
                ) {
                   Text(
                       text = priorityList[it],
                       color = MainFontColor
                   )
                }
            }
        }
    }
}

@Composable
fun TaskOptionSection(
    modifier: Modifier = Modifier
) {
    var selectedInterval by remember {
        mutableStateOf(0)
    }
    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LightContainerGray)
    ){
        Column {
            Text(
                text = "Tasks",
                color = MainFontColor
            )
            LazyRow(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items(45) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(width = 100.dp , height = 50.dp)
                            .padding(horizontal = 10.dp)
                            .clickable { selectedInterval = it }
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if(selectedInterval == it){
                                    SelectedBlueColor
                                } else {
                                    UnselectedGrayColor
                                }
                            )
                    ){
                        Text(
                            text = "$it",
                            color = MainFontColor,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun AddTaskNameDetailPreview() {
    AddTaskNameDetailSection(Modifier.fillMaxWidth())
}