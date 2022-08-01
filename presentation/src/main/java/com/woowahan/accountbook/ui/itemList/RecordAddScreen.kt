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
import com.woowahan.accountbook.ui.component.InputDateItem
import com.woowahan.accountbook.ui.component.InputPriceItem
import com.woowahan.accountbook.ui.component.LargeButton
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.theme.LightPurple
import dagger.hilt.android.lifecycle.HiltViewModel
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

                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = LightPurple
                    )

                    InputPriceItem(title = "금액", viewModel.price)
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = LightPurple
                    )

                    if (!isIncomeClicked) {
                        InputSpinnerItem(
                            title = "결제수단",
                            list = viewModel.payments.value!!.map {
                                it.name
                            }
                        ) {
                            viewModel.payment.value.name = it
                        }

                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = LightPurple
                        )
                    }

                    InputSpinnerItem(
                        title = "분류",
                        list = if (isIncomeClicked) {
                            viewModel.income.value!!.map {
                                it.name
                            }
                        } else {
                            viewModel.spending.value!!.map {
                                it.name
                            }
                        }
                    ) {
                        viewModel.category.value.name = it
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = LightPurple
                    )

                    InputTextItem(title = "내용", viewModel.content)

                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = LightPurple
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            LargeButton(
                modifier = Modifier.padding(16.dp, 10.dp, 16.dp, 20.dp),
                enabled = viewModel.isValid(isIncomeClicked),
            ) {
                if (isIncomeClicked) {
                    coroutineScope.launch {
                        viewModel.addIncomeRecord()
                    }
                    navController.popBackStack()
                }
            }
        }
    }
}