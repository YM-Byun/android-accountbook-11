package com.woowahan.accountbook.ui.itemList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.*
import com.woowahan.accountbook.ui.theme.LightPurple
import kotlinx.coroutines.launch

@Composable
fun RecordAddScreen(
    navController: NavController,
    recordAddViewModel: RecordAddViewModel
) {
    var isIncomeClicked by remember { mutableStateOf(true) }
    val viewModel = remember { recordAddViewModel }
    val coroutineScope = rememberCoroutineScope()
    viewModel.getOptions()

    Scaffold(
        topBar = {
            TopAppBar(
                title = "내역 등록",
                btn1Image = R.drawable.ic_back,
                btn1OnClick = {
                    navController.popBackStack()
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
                    InputDateItem(title = "일자", viewModel.date)

                    LightDivider(padding = 0)

                    InputPriceItem(title = "금액", viewModel.price)

                    LightDivider(padding = 0)

                    if (!isIncomeClicked) {
                        InputPaymentSpinnerItem(
                            title = "결제수단",
                            list = viewModel.payments.value!!
                        ) {
                            viewModel.payment.value = it
                        }

                        LightDivider(padding = 0)
                    }

                    InputCategorySpinnerItem(
                        title = "분류",
                        list = if (isIncomeClicked) {
                            viewModel.income.value!!
                        } else {
                            viewModel.spending.value!!
                        }
                    ) {
                        viewModel.category.value = it
                    }

                    LightDivider(padding = 0)

                    InputTextItem(title = "내용", viewModel.content)

                    LightDivider(padding = 0)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            LargeButton(
                modifier = Modifier.padding(16.dp, 10.dp, 16.dp, 20.dp),
                enabled = viewModel.isValid(isIncomeClicked),
                text = "등록하기"
            ) {
                if (isIncomeClicked) {
                    coroutineScope.launch {
                        viewModel.addIncomeRecord()
                    }
                } else {
                    coroutineScope.launch {
                        viewModel.addSpendingRecord()
                    }
                }
                navController.popBackStack()
            }
        }
    }
}