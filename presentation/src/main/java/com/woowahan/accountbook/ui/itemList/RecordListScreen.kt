package com.woowahan.accountbook.ui.itemList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel

@Composable
fun RecordListScreen(viewModel: MainViewModel) {
    val title = viewModel.currentScreen.observeAsState("").value
    val recordViewModel = remember { RecordViewModel() }
    val records = recordViewModel.records.observeAsState().value!!
    val leftClicked = recordViewModel.leftBtnOnClick.observeAsState().value!!
    val rightClicked = recordViewModel.rightBtnOnClick.observeAsState().value!!

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
        floatingActionButton = { FloatingActionButton({}) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            FilterButton(
                showCheckBox = true,
                isLeftChecked = leftClicked,
                isRightChecked = rightClicked,
                leftText = "수입 1,000원",
                rightText = "지출 29,000원",
                modifier = Modifier.padding(16.dp),
                leftOnClick = {
                    recordViewModel.leftBtnOnClick.postValue(!recordViewModel.leftBtnOnClick.value!!)
                },
                rightOnClick = {
                    recordViewModel.rightBtnOnClick.postValue(!recordViewModel.rightBtnOnClick.value!!)
                }
            )
            LazyColumn {
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