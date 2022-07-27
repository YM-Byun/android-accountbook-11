package com.woowahan.accountbook.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.woowahan.accountbook.ui.theme.Green1
import com.woowahan.accountbook.ui.theme.Green4

@Composable
fun CalendarScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Green4)
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