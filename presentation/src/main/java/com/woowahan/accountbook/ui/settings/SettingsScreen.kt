package com.woowahan.accountbook.ui.settings

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.woowahan.accountbook.ui.component.HeaderTextView
import com.woowahan.accountbook.ui.component.LightDivider
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.navigate.ADD_INCOME
import com.woowahan.accountbook.ui.navigate.ADD_PAYMENTS
import com.woowahan.accountbook.ui.navigate.ADD_SPENDING
import com.woowahan.accountbook.ui.theme.incomeColors
import com.woowahan.accountbook.ui.theme.spendingColors
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel
) {
    val viewModel = remember { settingsViewModel }

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
                    HeaderTextView(header = "결제수단")
                }
                items(
                    items = payments,
                    itemContent = {
                        SettingsItemWithNoCategory(text = it.name)
                    }
                )
                item {
                    SettingsAddItem(text = "결제수단 추가하기") {
                        navController.navigate(ADD_PAYMENTS) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    LightDivider()
                }

                item {
                    HeaderTextView(header = "지출 카테고리")
                }
                items(
                    items = spending,
                    itemContent = {
                        SettingsItemWithCategory(it.name, spendingColors[0])
                    }
                )
                item {
                    SettingsAddItem(text = "지출 카테고리 추가하기") {
                        navController.navigate(ADD_SPENDING) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    LightDivider()
                }

                item {
                    HeaderTextView(header = "수입 카테고리")
                }
                items(
                    items = income,
                    itemContent = {
                        SettingsItemWithCategory(it.name, incomeColors[it.color])
                    }
                )
                item {
                    SettingsAddItem(text = "수입 카테고리 추가하기") {
                        navController.navigate(ADD_INCOME) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    LightDivider()
                }
            }
        }
    }
}