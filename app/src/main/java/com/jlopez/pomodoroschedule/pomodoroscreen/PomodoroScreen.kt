package com.jlopez.pomodoroschedule.pomodoroscreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jlopez.pomodoroschedule.R
import com.jlopez.pomodoroschedule.model.TaskEntry
import com.jlopez.pomodoroschedule.ui.theme.*
import com.jlopez.pomodoroschedule.util.parseTaskColor
import com.jlopez.pomodoroschedule.util.parseTaskColorBorder
import java.util.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState

val viewModel: PomodoroScreenViewModel = PomodoroScreenViewModel()

@Composable
fun PomodoroScreen() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            CurrentTaskSection(
                entry = TaskEntry(
                    taskName = "Mobile App Development",
                    description = "Work on app development",
                    priority = "High",
                    pomodoroRepetitions = 4,
                    pomodoroIntervals = 4,
                    pomodoroBreakAmount = 15,
                    timeRemaining = 30
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(50.dp))
            PomodoroTimerSectionDesignLogic(
                modifier= Modifier
                    .clip(RoundedCornerShape(100))
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(100))
            )
            Spacer(modifier = Modifier.height(80.dp))
            PomodoroTimerButtonSection(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun CurrentTaskSection(
    entry: TaskEntry,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LightContainerGray)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp)
        ) {
            Box {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(
                        modifier = Modifier
                            .padding(15.dp)
                            .size(30.dp)
                    ) {
                        drawCircle(
                            color = parseTaskColor(entry),
                        )
                        drawCircle(
                            color = parseTaskColorBorder(entry),
                            style = Stroke(
                                width = 2.dp.toPx()
                            )
                        )
                    }
                    Column {
                        Text(
                            text = entry.taskName,
                            style = MaterialTheme.typography.body1,
                            color = MainFontColor
                        )
                        Text(
                            text = "${entry.timeRemaining} minutes",
                            style = MaterialTheme.typography.body2,
                            color = MainFontColor
                        )
                    }
                }
            }
            Box {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "1/${entry.pomodoroRepetitions}",
                            style = MaterialTheme.typography.body1,
                            color = MainFontColor,
                            modifier = Modifier
                                .align(Alignment.End)
                        )
                        Text(
                            text = "${entry.pomodoroIntervals} min",
                            style = MaterialTheme.typography.body2,
                            color = MainFontColor
                        )
                    }
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_cancel_task
                        ),
                        contentDescription = null,
                        tint = UnselectedGrayColor,
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .clickable { }
                            .size(30.dp)

                    )
                }
            }
        }
    }
}

@Composable
fun PomodoroTimerSection(
    pomodoroViewModel: PomodoroScreenViewModel,
    modifier: Modifier = Modifier
) {
    
}

@Composable
fun PomodoroTimerSectionDesignLogic(
    modifier: Modifier = Modifier
) {
    val currentPercentage: Float by viewModel.currentPercentage.observeAsState(1f)
    val timeInText: String by viewModel.timeInText.observeAsState("25:00")
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                Brush.sweepGradient(
                    listOf(
                        BackgroundGray,
                        BackgroundGray,
                        BackgroundGray,
                        LightContainerGray,
                        BackgroundGray
                    )
                )
            )
    ){
        Canvas(modifier = Modifier
            .padding(15.dp)
            .size(300.dp)
        ) {
            drawCircle(
                color = TimerBackground,
                radius = 450f
            )
            drawCircle(
                color = TimerInactiveIndicationColor,
                radius = 390f,
                style = Stroke(
                    width = 13.dp.toPx()
                )
            )
            drawArc(
                color = TimerActiveIndicationColor,
                startAngle = 90f,
                sweepAngle = 380f * currentPercentage,
                useCenter = false,
                topLeft = Offset(this.center.x - 390f, this.center.y - 390f),
                size = Size(780f,780f),
                style = Stroke(
                    width = 13.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            text = timeInText,
            style = MaterialTheme.typography.body1,
            color = MainFontColor,
            fontWeight = FontWeight.Bold,
            fontSize = 70.sp
        )
    }
}



@Composable
fun PomodoroTimerButtonSection(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = UnselectedBlueColor
            ),
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Text(
                text = "Stop",
                color = SelectedBlueColor,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp)
            )
        }
        Button(
            onClick = { viewModel.handlePomodoroTimer() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = SelectedBlueColor
            ),
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Text(
                text = "Start",
                color = MainFontColor,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp)
            )
        }
    }
}

@Preview
@Composable
fun PomodoroTimerButtonSectionPreview(){
    PomodoroTimerButtonSection()
}

@Preview
@Composable
fun PomodoroTimerSectionPreview() {
    PomodoroTimerSectionDesignLogic(Modifier.fillMaxWidth())
}

@Preview
@Composable
fun CurrentTaskSectionPreview() {
    CurrentTaskSection(
        entry = TaskEntry(
            taskName = "Mobile App Development",
            description = "Work on app development",
            priority = "High",
            pomodoroRepetitions = 4,
            pomodoroIntervals = 4,
            pomodoroBreakAmount = 15,
            timeRemaining = 30
        )
    )
}

