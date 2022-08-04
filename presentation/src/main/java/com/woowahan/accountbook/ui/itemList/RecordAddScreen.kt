package com.woowahan.accountbook.ui.itemList

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.*
import com.woowahan.accountbook.ui.main.SharedViewModel
import com.woowahan.accountbook.ui.navigate.ADD_INCOME
import com.woowahan.accountbook.ui.navigate.ADD_PAYMENTS
import com.woowahan.accountbook.ui.navigate.ADD_SPENDING
import com.woowahan.data.entity.DBHelper
import kotlinx.coroutines.launch

@Composable
fun RecordAddScreen(
    navController: NavController,
    recordAddViewModel: RecordAddViewModel,
    sharedViewModel: SharedViewModel,
    refreshRecord: () -> Unit
) {
    var isIncomeClicked by remember { mutableStateOf(true) }
    var isUpdateMode by remember { mutableStateOf(false) }

    val viewModel = remember { recordAddViewModel }
    val coroutineScope = rememberCoroutineScope()
    viewModel.getOptions()

    val backPressed = {
        viewModel.init()
        sharedViewModel.init()
        navController.popBackStack()
    }

    sharedViewModel.getSharedRecord()?.let {
        recordAddViewModel.loadRecord(it)
        isIncomeClicked = (it.type == DBHelper.INCOME)
        isUpdateMode = true
    }

    BackHandler {
        backPressed()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = "내역 등록",
                btn1Image = R.drawable.ic_back,
                btn1OnClick = {
                    backPressed()
                },
                btn2Image = null,
                btn2OnClick = {}
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            FilterButton(
                showCheckBox = false,
                isLeftChecked = isIncomeClicked,
                isRightChecked = !isIncomeClicked,
                leftText = "수입",
                rightText = "지출",
                modifier = Modifier.padding(16.dp),
                leftOnClick = {
                    isIncomeClicked = true
                    viewModel.init()
                },
                rightOnClick = {
                    isIncomeClicked = false
                    viewModel.init()
                }
            )

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                item {
                    InputDateItem(title = "일자", viewModel.date) {
                        viewModel.date = it
                    }

                    LightDivider(padding = 0)

                    InputPriceItem(title = "금액", viewModel.price) {
                        viewModel.price = it
                    }

                    LightDivider(padding = 0)

                    if (!isIncomeClicked) {
                        InputPaymentSpinnerItem(
                            title = "결제수단",
                            list = viewModel.payments.value!!,
                            onValueSelected = {
                                viewModel.payment = it
                            },
                            onAddItemListener = {
                                navController.navigate(ADD_PAYMENTS) {
                                    navController.graph.startDestinationRoute
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            defaultPayment = viewModel.payment
                        )

                        LightDivider(padding = 0)
                    }

                    InputCategorySpinnerItem(
                        title = "분류",
                        list = if (isIncomeClicked) {
                            viewModel.income.value!!
                        } else {
                            viewModel.spending.value!!
                        },
                        onAddItemListener = {
                            val route = if (isIncomeClicked) {
                                ADD_INCOME
                            } else {
                                ADD_SPENDING
                            }
                            navController.navigate(route) {
                                navController.graph.startDestinationRoute
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        onValueSelected = {
                            viewModel.category = it
                        },
                        defaultCategory = viewModel.category
                    )

                    LightDivider(padding = 0)

                    InputTextItem(title = "내용", viewModel.content) {
                        viewModel.content = it
                    }

                    LightDivider(padding = 0)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            LargeButton(
                modifier = Modifier.padding(16.dp, 10.dp, 16.dp, 20.dp),
                enabled = viewModel.isValid(isIncomeClicked),
                text = if (isUpdateMode) "수정하기" else "등록하기"
            ) {
                if (isUpdateMode) {
                    coroutineScope.launch {
                        viewModel.updateRecord()
                        refreshRecord()
                    }
                } else {
                    if (isIncomeClicked) {
                        coroutineScope.launch {
                            viewModel.addIncomeRecord()
                        }
                    } else {
                        coroutineScope.launch {
                            viewModel.addSpendingRecord()
                        }
                    }
                }
                navController.popBackStack()
            }
        }
    }
}