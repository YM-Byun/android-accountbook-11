package com.woowahan.accountbook.ui.itemList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.woowahan.accountbook.R
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
import com.woowahan.accountbook.ui.component.*
import com.woowahan.accountbook.ui.main.MainViewModel
import com.woowahan.accountbook.ui.main.SharedViewModel
import com.woowahan.accountbook.ui.navigate.ADD_ITEM
import com.woowahan.accountbook.ui.theme.Purple
import com.woowahan.data.entity.DBHelper
import com.woowahan.domain.model.Record
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordListScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    recordViewModel: RecordListViewModel,
    sharedViewModel: SharedViewModel
) {
    val coroutineScope = rememberCoroutineScope()

    val title = mainViewModel.appBarTitle.observeAsState().value!!

    var isIncomeClicked by remember { mutableStateOf(true) }
    var isSpendingClicked by remember { mutableStateOf(false) }

    var selectMode by remember { mutableStateOf(false) }
    val selectedItems = remember { mutableStateListOf<Record>() }

    val originRecords = mainViewModel.records.observeAsState().value!!
    val records =
        getRecordsFilterBy(originRecords, isIncomeClicked, isSpendingClicked)

    var showPicker by remember { mutableStateOf(false) }

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
                        coroutineScope.launch {
                            recordViewModel.deleteItems(selectedItems)
                            mainViewModel.getRecords()
                            selectedItems.clear()
                            selectMode = false
                        }
                    }
                )
            } else {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (showPicker) {
                MonthPicker(
                    initYear = title.year(),
                    initMonth = title.month(),
                    onDismissRequest = { showPicker = false }) { nYear, nMonth ->
                    mainViewModel.onDatePicked(nYear, nMonth)
                    showPicker = false
                }
            }
            Column {
                FilterButton(
                    showCheckBox = true,
                    isLeftChecked = isIncomeClicked,
                    isRightChecked = isSpendingClicked,
                    leftText = "수입 ${String.format("%,d", mainViewModel.totalIncome)}",
                    rightText = "지출 ${String.format("%,d", mainViewModel.absTotalSpending)}",
                    modifier = Modifier.padding(16.dp),
                    leftOnClick = {
                        isIncomeClicked = !isIncomeClicked
                    },
                    rightOnClick = {
                        isSpendingClicked = !isSpendingClicked
                    }
                )

                RecordList(
                    records = records,
                    selectedItem = selectedItems,
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
                        } else {
                            sharedViewModel.sharingRecord(it)
                            navController.navigate(ADD_ITEM) {
                                navController.graph.startDestinationRoute?.let {
                                    popUpTo(it) { saveState = true }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    onLongClick = {
                        selectMode = true
                        if (!selectedItems.contains(it)) {
                            selectedItems.add(it)
                        }
                    })
            }
        }
    }
}

fun getRecordsFilterBy(
    records: List<Record>,
    isIncomeClicked: Boolean,
    isSpendingClicked: Boolean
): List<Record> {
    return (if (isIncomeClicked && isSpendingClicked) {
        records
    } else if (isIncomeClicked) {
        records.filter {
            it.type == DBHelper.INCOME
        }
    } else if (isSpendingClicked) {
        records.filter {
            it.type == DBHelper.SPENDING
        }
    } else {
        emptyList()
    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordList(
    records: List<Record>,
    selectedItem: List<Record>,
    onClick: (Record) -> Unit,
    onLongClick: (Record) -> Unit
) {
    if (records.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "내역이 없습니다",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Purple
            )
        }
    } else {
        LazyColumn {
            records.groupBy { it.day }.forEach { entry ->
                val month = entry.value.first().month
                val day = entry.value.first().day
                val total = getTotalIncomeSpending(entry.value)

                item {
                    RecordHeader(
                        header = "${month}월 ${day}일",
                        income = total.first,
                        spending = total.second
                    )

                    LightDivider(padding = 16)
                }
                item {
                    entry.value.forEachIndexed { index, it ->
                        RecordItem(
                            recordType = it.type,
                            paymentType = it.payment.name,
                            content = it.content,
                            price = it.price,
                            category = it.category,
                            onClick = { onClick(it) },
                            onLongClick = { onLongClick(it) },
                            isSelected = selectedItem.contains(it),
                        )

                        if (index == entry.value.lastIndex) {
                            BoldDivider()
                        } else {
                            LightDivider(16)
                        }
                    }
                }
            }
        }
    }
}

fun getTotalIncomeSpending(records: List<Record>): Pair<Long, Long> {
    var income = 0L
    var spending = 0L

    records.forEach {
        if (it.type == DBHelper.SPENDING) {
            spending += (it.price * -1)
        } else {
            income += it.price
        }
    }

    return Pair(income, spending)
}