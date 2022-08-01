package com.woowahan.accountbook.ui.settings

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
import com.woowahan.accountbook.ui.navigate.ADD_INCOME
import com.woowahan.accountbook.ui.navigate.ADD_PAYMENTS
import com.woowahan.accountbook.ui.navigate.ADD_SPENDING
import kotlinx.coroutines.launch

@Composable
fun SettingAddScreen(
    navController: NavController,
    mode: String,
    settingsAddViewModel: SettingsAddViewModel
) {
    var viewModel = remember { settingsAddViewModel }
    val colors = viewModel.getColors(mode)
    var selectedColor by viewModel.selectedColorIdx
    val coroutineScope = rememberCoroutineScope()

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
                    viewModel.init()
                    navController.popBackStack()
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
                    InputTextItem(title = "이름", content = viewModel.name, padding = 16)
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
                                    selectedColor = colors.indexOf(it)
                                }
                        ) {
                            val size = if (selectedColor == colors.indexOf(it)) {
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
                enabled = viewModel.isValid()
            ) {
                when (mode) {
                    ADD_PAYMENTS ->
                        coroutineScope.launch {
                            viewModel.addPayment(viewModel.name.value)
                        }
                    ADD_INCOME ->
                        coroutineScope.launch {
                            viewModel.addIncomeCategory(
                                viewModel.name.value,
                                viewModel.selectedColorIdx.value
                            )
                        }
                    ADD_SPENDING ->
                        coroutineScope.launch {
                            viewModel.addSpendingCategory(
                                viewModel.name.value,
                                viewModel.selectedColorIdx.value
                            )
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
            for (colIdx in 0 until 10) {
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