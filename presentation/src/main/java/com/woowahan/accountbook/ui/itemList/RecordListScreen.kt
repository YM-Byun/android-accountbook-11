package com.woowahan.accountbook.ui.itemList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel
import com.woowahan.accountbook.ui.navigate.ADD_ITEM
import com.woowahan.domain.model.Record

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordListScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val title = viewModel.currentScreen.observeAsState("").value
    val recordViewModel = remember { RecordViewModel() }
    val records = recordViewModel.records.observeAsState().value
    val leftClicked = recordViewModel.leftBtnOnClick.observeAsState().value!!
    val rightClicked = recordViewModel.rightBtnOnClick.observeAsState().value!!
    var selectMode by remember { mutableStateOf(false) }
    val selectedItems = remember { mutableStateListOf<Record>() }

    Scaffold(
        topBar = {
            if (selectMode) {
                TopAppBar(
                    title = "${selectedItems.size}개 선택",
                    btn1Image = R.drawable.ic_back,
                    btn1OnClick = {
                        selectMode = false
                        selectedItems.clear()
                    },
                    btn2Image = R.drawable.ic_trash,
                    btn2OnClick = {

                    }
                )
            } else {
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
            }
        },
        floatingActionButton = {
            FloatingActionButton {
                navController.navigate(ADD_ITEM) {
                    navController.graph.startDestinationRoute?.let {
                        popUpTo(it) { saveState = true }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
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

            if (records == null) {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = "내역이 없습니다", fontSize = 12.sp, textAlign = TextAlign.Center
                )
            } else {
                if (records.isEmpty()) {
                    Text(text = "내역이 없습니다", fontSize = 12.sp, textAlign = TextAlign.Center)
                } else {
                    LazyColumn {
                        items(
                            items = records,
                            itemContent = {
                                RecordItem(
                                    recordType = it.type,
                                    paymentType = it.payment.name,
                                    content = it.content,
                                    price = it.price.toString(),
                                    category = it.category,
                                    onClick = {
                                        if (selectMode) {
                                            if (selectedItems.contains(it)) {
                                                selectedItems.remove(it)

                                                if (selectedItems.isEmpty()) {
                                                    selectMode = false
                                                }
                                            } else {
                                                selectedItems.add(it)
                                            }
                                        }
                                    },
                                    onLongClick = {
                                        selectMode = true
                                        if (!selectedItems.contains(it)) {
                                            selectedItems.add(it)
                                        }
                                    },
                                    isSelected = selectedItems.contains(it),
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}