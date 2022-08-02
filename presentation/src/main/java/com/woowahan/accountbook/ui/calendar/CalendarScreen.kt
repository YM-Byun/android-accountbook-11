package com.woowahan.accountbook.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.woowahan.accountbook.R
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel
import com.woowahan.accountbook.ui.main.RecordViewModel
import com.woowahan.accountbook.ui.theme.Green1
import com.woowahan.accountbook.ui.theme.Green4
import java.util.*

@Composable
fun CalendarScreen(
    mainViewModel: MainViewModel,
    recordViewModel: RecordViewModel
) {
    val title by mainViewModel.appBarTitle.observeAsState("")
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
                .background(Green4)
                .padding(it)
        ) {
            val firstDay = Calendar.getInstance().set(title.year(), title.month(), 1)
            LazyVerticalGrid(columns = GridCells.Fixed(7)) {

            }
        }
    }
}