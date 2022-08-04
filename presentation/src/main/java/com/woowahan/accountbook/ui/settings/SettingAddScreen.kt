package com.woowahan.accountbook.ui.settings

import android.os.Looper
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.*
import com.woowahan.accountbook.ui.itemList.InputTextItem
import com.woowahan.accountbook.ui.main.SharedViewModel
import com.woowahan.accountbook.ui.navigate.ADD_INCOME
import com.woowahan.accountbook.ui.navigate.ADD_PAYMENTS
import com.woowahan.accountbook.ui.navigate.ADD_SPENDING
import kotlinx.coroutines.launch

@Composable
fun SettingAddScreen(
    navController: NavController,
    mode: String,
    settingsAddViewModel: SettingsAddViewModel,
    sharedViewModel: SharedViewModel,
    refreshMethod: () -> Unit
) {
    val colors = settingsAddViewModel.getColors(mode)
    val coroutineScope = rememberCoroutineScope()
    var isUpdateMode by remember { mutableStateOf(false) }

    sharedViewModel.getSharedCategory()?.let {
        settingsAddViewModel.loadCategory(it)
        isUpdateMode = true
    }

    sharedViewModel.getSharedPayment()?.let {
        settingsAddViewModel.loadPayment(it)
        isUpdateMode = true
    }

    val backPressed = {
        settingsAddViewModel.init()
        sharedViewModel.init()
        navController.popBackStack()
    }

    BackHandler {
        backPressed()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = if (mode == ADD_PAYMENTS) {
                    "결제 수단 추가하기"
                } else if (mode == ADD_INCOME) {
                    "수입 카테고리 추기"
                } else {
                    "지출 카테고리 추가"
                },
                btn1Image = R.drawable.ic_back,
                btn1OnClick = {
                    settingsAddViewModel.init()
                    backPressed()
                },
                btn2Image = null,
                btn2OnClick = {}
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn {
                item {
                    InputTextItem(title = "이름", content = settingsAddViewModel.name, padding = 16) {
                        settingsAddViewModel.name = it
                    }
                    LightDivider(16)
                    if (mode != ADD_PAYMENTS) {
                        Spacer(modifier = Modifier.height(10.dp))
                        HeaderTextView(header = "색상")
                        LightDivider(16)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                if (mode != ADD_PAYMENTS) {
                    colorPalette(colors) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clickable {
                                    settingsAddViewModel.selectedColorIdx = colors.indexOf(it)
                                }
                        ) {
                            val size =
                                if (settingsAddViewModel.selectedColorIdx == colors.indexOf(it)) {
                                    22
                                } else {
                                    18
                                }
                            Spacer(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(size.dp)
                                    .background(it)
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        BoldDivider()
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            LargeButton(
                modifier = Modifier.padding(16.dp),
                enabled = settingsAddViewModel.isValid(),
                text = if (isUpdateMode) "수정하기" else "등록하기"
            ) {
                if (isUpdateMode) {
                    when (mode) {
                        ADD_PAYMENTS ->
                            coroutineScope.launch {
                                settingsAddViewModel.updatePayment(settingsAddViewModel.name)
                            }
                        else -> {
                            coroutineScope.launch {
                                settingsAddViewModel.updateCategory(
                                    settingsAddViewModel.name,
                                    settingsAddViewModel.selectedColorIdx
                                )
                            }
                        }
                    }
                    android.os.Handler(Looper.getMainLooper()).postDelayed(
                        {
                            refreshMethod()
                        }, 50
                    )
                } else {
                    when (mode) {
                        ADD_PAYMENTS ->
                            coroutineScope.launch {
                                settingsAddViewModel.addPayment(settingsAddViewModel.name)
                            }
                        ADD_INCOME ->
                            coroutineScope.launch {
                                settingsAddViewModel.addIncomeCategory(
                                    settingsAddViewModel.name,
                                    settingsAddViewModel.selectedColorIdx
                                )
                            }
                        ADD_SPENDING ->
                            coroutineScope.launch {
                                settingsAddViewModel.addSpendingCategory(
                                    settingsAddViewModel.name,
                                    settingsAddViewModel.selectedColorIdx
                                )
                            }
                    }
                }
                navController.popBackStack()
            }
        }
    }
}

fun LazyListScope.colorPalette(
    colors: List<Color>,
    itemContent: @Composable BoxScope.(Color) -> Unit,
) {
    val rows = if (colors.isEmpty()) {
        0
    } else {
        1 + (colors.count() - 1) / 10
    }

    items(rows) { rowIdx ->
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (colIdx in 1 until 11) {
                val itemIdx = rowIdx * 10 + colIdx

                if (itemIdx < colors.count()) {
                    Box(
                        modifier = Modifier.weight(1f, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent.invoke(this, colors[itemIdx])
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f, fill = true))
                }
            }
        }
    }
}