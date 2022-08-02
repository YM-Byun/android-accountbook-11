package com.woowahan.accountbook.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel
import com.woowahan.accountbook.ui.theme.*
import com.woowahan.domain.model.Calendar

@Composable
fun CalendarScreen(
    mainViewModel: MainViewModel,
) {
    val title by mainViewModel.appBarTitle.observeAsState("")
    val calendarViewModel =  CalendarViewModel()
    calendarViewModel.parseCalendar(title, mainViewModel.records.value!!)

    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                btn1Image = R.drawable.ic_left,
                btn1OnClick = {
                    mainViewModel.onScreenChange("prev")
                },
                btn2Image = R.drawable.ic_right,
                btn2OnClick = {
                    mainViewModel.onScreenChange("next")
                }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            LazyVerticalGrid(columns = GridCells.Fixed(7)) {
                items(
                    items = calendarViewModel.calendarData.value!!
                ) {
                    CalendarItem(it)
                }
            }
        }
    }
}

@Composable
fun CalendarItem(calendar: Calendar) {
    val color = if (calendar.isToday) {
        White
    } else {
        Color.Transparent
    }
    val modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color)
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