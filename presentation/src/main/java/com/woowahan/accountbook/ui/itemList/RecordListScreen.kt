package com.woowahan.accountbook.ui.itemList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel

@Composable
fun RecordListScreen(viewModel: MainViewModel) {
    val title = viewModel.currentScreen.observeAsState("").value
    val recordViewModel = remember { RecordViewModel() }
    val records = recordViewModel.records.observeAsState().value!!

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
                .padding(it)
        ) {
            LazyColumn() {
                items(
                    items = records,
                    itemContent = {
                        RecordItem(
                            recordType = it.type,
                            paymentType = it.payment,
                            title = it.title,
                            amount = it.amount
                        )
                    }
                )
            }
        }
    }
}