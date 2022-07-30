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
import com.woowahan.accountbook.ui.component.LargeButton
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.theme.LightPurple

@Composable
fun RecordAddScreen(
    navController: NavController
) {
    var isIncomeClicked by remember { mutableStateOf(true) }
    val viewModel = remember { RecordAddViewModel() }

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
                            list = listOf("카드", "현금"),
                            viewModel.payments
                        )

                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = LightPurple
                        )
                    }

                    InputSpinnerItem(
                        title = "분류",
                        list = listOf("월급", "금융수입"),
                        viewModel.category
                    )
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

            }
        }
    }
}