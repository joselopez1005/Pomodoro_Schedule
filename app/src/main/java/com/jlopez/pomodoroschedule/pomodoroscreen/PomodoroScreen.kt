package com.jlopez.pomodoroschedule.pomodoroscreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlopez.pomodoroschedule.R
import com.jlopez.pomodoroschedule.model.TaskEntry
import com.jlopez.pomodoroschedule.ui.theme.LightContainerGray
import com.jlopez.pomodoroschedule.ui.theme.MainFontColor
import com.jlopez.pomodoroschedule.ui.theme.UnselectedGrayColor
import com.jlopez.pomodoroschedule.util.parseTaskColor
import com.jlopez.pomodoroschedule.util.parseTaskColorBorder

@Composable
fun PomodoroScreen() {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxSize()
    ) {

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
                            .clickable {  }
                            .size(30.dp)

                    )
                }
            }
        }
    }
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

