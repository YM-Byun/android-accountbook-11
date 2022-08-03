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
    val records = mainViewModel.records.observeAsState().value!!
    calendarViewModel.getCalendarData(title, records)

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
                CalendarText(text = "수입", amount = mainViewModel.totalIncome, color = Green6)
                LightDivider(padding = 16)
                CalendarText(text = "지출", amount = mainViewModel.totalSpending, color = Red)
                LightDivider(padding = 16)
                CalendarText(text = "총합", amount = mainViewModel.totalAmount, color = Purple)
                Spacer(modifier = Modifier.height(10.dp))
                BoldDivider()
            }
        }
    }
}