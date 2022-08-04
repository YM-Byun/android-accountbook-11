package com.woowahan.accountbook.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.woowahan.accountbook.ui.component.BoldDivider
import com.woowahan.accountbook.ui.component.HeaderTextView
import com.woowahan.accountbook.ui.component.LightDivider
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.SharedViewModel
import com.woowahan.accountbook.ui.navigate.ADD_INCOME
import com.woowahan.accountbook.ui.navigate.ADD_ITEM
import com.woowahan.accountbook.ui.navigate.ADD_PAYMENTS
import com.woowahan.accountbook.ui.navigate.ADD_SPENDING
import com.woowahan.accountbook.ui.theme.IncomeColors
import com.woowahan.accountbook.ui.theme.SpendingColors
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel,
    sharedViewModel: SharedViewModel
) {
    val viewModel = remember { settingsViewModel }

    viewModel.getSettings()

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
                    LightDivider(padding = 16)
                }
                items(
                    items = payments,
                    itemContent = {
                        SettingsItemWithNoCategory(text = it.name) {
                            sharedViewModel.sharingPayment(it)
                            navController.navigate(ADD_PAYMENTS) {
                                navController.graph.startDestinationRoute?.let {
                                    popUpTo(it) { saveState = true }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        LightDivider(padding = 16)
                    }
                )
                item {
                    SettingsAddItem(text = "결제수단 추가하기") {
                        navController.navigate(ADD_PAYMENTS) {
                            navController.graph.startDestinationRoute
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    BoldDivider()
                }

                item {
                    HeaderTextView(header = "지출 카테고리")
                }
                items(
                    items = spending,
                    itemContent = {
                        if (it.name != "미분류") {
                            SettingsItemWithCategory(it.name, SpendingColors[it.color]) {
                                sharedViewModel.sharingCategory(it)
                                navController.navigate(ADD_SPENDING) {
                                    navController.graph.startDestinationRoute?.let {
                                        popUpTo(it) { saveState = true }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            LightDivider(padding = 16)
                        }
                    }
                )
                item {
                    SettingsAddItem(text = "지출 카테고리 추가하기") {
                        navController.navigate(ADD_SPENDING) {
                            navController.graph.startDestinationRoute
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    BoldDivider()
                }

                item {
                    HeaderTextView(header = "수입 카테고리")
                }
                items(
                    items = income,
                    itemContent = {
                        if (it.name != "미분류") {
                            SettingsItemWithCategory(it.name, IncomeColors[it.color]) {
                                sharedViewModel.sharingCategory(it)
                                navController.navigate(ADD_INCOME) {
                                    navController.graph.startDestinationRoute?.let {
                                        popUpTo(it) { saveState = true }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            LightDivider(padding = 16)
                        }
                    }
                )
                item {
                    SettingsAddItem(text = "수입 카테고리 추가하기") {
                        navController.navigate(ADD_INCOME) {
                            navController.graph.startDestinationRoute
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    BoldDivider()
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}