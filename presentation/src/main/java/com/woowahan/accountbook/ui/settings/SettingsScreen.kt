package com.woowahan.accountbook.ui.settings

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment

@Composable
fun SettingsScreen(context: Context) {
    val viewModel = SettingsViewModel()
    val payments: List<Payment> = viewModel.payments.observeAsState().value!!
    val spending: List<Category> = viewModel.spending.observeAsState().value!!
    val income: List<Category> = viewModel.income.observeAsState().value!!

    Scaffold(
        topBar = {
            TopAppBar(
                title = "설정",
                btn1Image = null,
                btn1OnClick = {},
                btn2Image = null
            ) {}
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn {
                item {
                    SettingsHeader(header = "결제수단")
                }
                items(
                    items = payments,
                    itemContent = {
                        SettingsItemWithNoCategory(text = it.name)
                    }
                )
                item {
                    SettingsAddItem(text = "결제수단 추가하기") {
                        Toast.makeText(context, "구현 예정", Toast.LENGTH_SHORT).show()
                    }
                }

                item {
                    SettingsHeader(header = "지출 카테고리")
                }
                items(
                    items = spending,
                    itemContent = {
                        SettingsItemWithCategory(it.name)
                    }
                )
                item {
                    SettingsAddItem(text = "지출 카테고리 추가하기") {
                        Toast.makeText(context, "구현 예정", Toast.LENGTH_SHORT).show()
                    }
                }

                item {
                    SettingsHeader(header = "수입 카테고리")
                }
                items(
                    items = income,
                    itemContent = {
                        SettingsItemWithCategory(it.name)
                    }
                )
                item {
                    SettingsAddItem(text = "수입 카테고리 추가하기") {
                        Toast.makeText(context, "구현 예정", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}