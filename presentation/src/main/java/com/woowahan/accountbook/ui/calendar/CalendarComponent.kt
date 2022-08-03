package com.woowahan.accountbook.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.ui.theme.*
import com.woowahan.domain.model.Calendar


@Composable
fun CalendarText(text: String, amount: Long, color: Color) {
    Row(modifier = Modifier.padding(16.dp, 7.dp, 16.dp, 7.dp)) {
        Text(text = text, color = color, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = String.format("%,d", amount),
            color = color,
            fontSize = 16.sp
        )
    }
}

@Composable
fun CalendarItem(calendar: Calendar) {
    val modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(if (calendar.isToday) White else Color.Transparent)
        .drawBehind {
            drawLine(
                LightPurple,
                Offset(0f, size.height),
                Offset(size.width, size.height),
                1.dp.value
            )
            drawLine(
                LightPurple,
                Offset(size.width, 0f),
                Offset(size.width, size.height),
                1.dp.value
            )
        }

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Column {
                if (calendar.income != "0") {
                    Text(text = calendar.income, fontSize = 8.sp, color = Green6)
                }
                if (calendar.spending != "0") {
                    Text(text = calendar.spending, fontSize = 8.sp, color = Red)
                }
                if (
                    !(calendar.income == "0" &&
                            calendar.spending == "0" &&
                            calendar.total == "0") &&
                    calendar.isCurrentMonth
                )
                    Text(text = calendar.total, fontSize = 8.sp, color = Purple)
            }

            Text(
                modifier = Modifier.align(Alignment.BottomEnd),
                text = calendar.day.toString(),
                color = if (calendar.isCurrentMonth) {
                    Purple
                } else {
                    Purple40
                },
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}