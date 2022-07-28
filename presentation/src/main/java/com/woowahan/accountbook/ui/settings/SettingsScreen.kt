package com.woowahan.accountbook.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel
import com.woowahan.accountbook.ui.settings.SettingsItemWithCategory
import com.woowahan.accountbook.ui.theme.Blue1
import com.woowahan.accountbook.ui.theme.Blue4

@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "설정",
                btn1Image = null,
                btn1OnClick = {},
                btn2Image = null
            ) {}
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn() {
                itemsIndexed(listOf("test1", "test2", "test3")) { index, item ->
                    SettingsItemWithCategory(text = item, category = item)
                }
            }
        }
    }
}