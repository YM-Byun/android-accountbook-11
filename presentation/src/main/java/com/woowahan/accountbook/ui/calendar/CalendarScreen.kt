package com.woowahan.accountbook.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
import com.woowahan.accountbook.ui.component.BoldDivider
import com.woowahan.accountbook.ui.component.LightDivider
import com.woowahan.accountbook.ui.component.MonthPicker
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel
import com.woowahan.accountbook.ui.theme.*
import com.woowahan.domain.model.Calendar

@Composable
fun CalendarScreen(
    mainViewModel: MainViewModel,
    calendarViewModel: CalendarViewModel
) {
    val title by mainViewModel.appBarTitle.observeAsState("")
    calendarViewModel.getCalendarData(title)

    var showPicker by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                btn1Image = R.drawable.ic_left,
                btn1OnClick = {
                    mainViewModel.onPrevClicked()
                },
                btn2Image = R.drawable.ic_right,
                btn2OnClick = {
                    mainViewModel.onNextClicked()
                },
                titleOnClick = {
                    showPicker = true
                }
            )
        },
    ) {
        Box {
            if (showPicker) {
                MonthPicker(
                    initYear = title.year(),
                    initMonth = title.month(),
                    onDismissRequest = { showPicker = false }) { nYear, nMonth ->
                    mainViewModel.onDatePicked(nYear, nMonth)
                    showPicker = false
                }
            }
            Column(
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
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.padding(16.dp, 7.dp, 16.dp, 7.dp)) {
                    Text(text = "수입", color = Purple, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = String.format("%,d", calendarViewModel.totalIncome),
                        color = Green6,
                        fontSize = 16.sp
                    )
                }
                LightDivider(padding = 16)
                Row(modifier = Modifier.padding(16.dp, 7.dp, 16.dp, 7.dp)) {
                    Text(text = "지출", color = Red, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = String.format("%,d", calendarViewModel.totalSpending),
                        color = Red,
                        fontSize = 16.sp
                    )
                }
                LightDivider(padding = 16)
                Row(modifier = Modifier.padding(16.dp, 7.dp, 16.dp, 7.dp)) {
                    Text(text = "총합", color = Purple, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = String.format("%,d", calendarViewModel.totalAmount),
                        color = Purple,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                BoldDivider()
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