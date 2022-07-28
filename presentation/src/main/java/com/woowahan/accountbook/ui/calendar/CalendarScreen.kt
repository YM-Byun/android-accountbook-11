package com.woowahan.accountbook.ui.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel
import com.woowahan.accountbook.ui.theme.Green1
import com.woowahan.accountbook.ui.theme.Green4

@Composable
fun CalendarScreen(viewModel: MainViewModel) {
    val title by viewModel.currentScreen.observeAsState("")
    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                btn1Image = R.drawable.ic_left,
                btn1OnClick = {
                    viewModel.onScreenChange("prev")
                },
                btn2Image = R.drawable.ic_right,
                btn2OnClick = {
                    viewModel.onScreenChange("next")
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
            Text(
                text = "Calendar",
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                color = Green1,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
    }
}