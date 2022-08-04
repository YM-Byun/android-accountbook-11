package com.woowahan.accountbook.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.woowahan.accountbook.ui.analysis.AnalysisScreen
import com.woowahan.accountbook.ui.analysis.AnalysisViewModel
import com.woowahan.accountbook.ui.calendar.CalendarScreen
import com.woowahan.accountbook.ui.calendar.CalendarViewModel
import com.woowahan.accountbook.ui.component.BottomNaviBar
import com.woowahan.accountbook.ui.itemList.RecordAddScreen
import com.woowahan.accountbook.ui.itemList.RecordAddViewModel
import com.woowahan.accountbook.ui.itemList.RecordListScreen
import com.woowahan.accountbook.ui.itemList.RecordListViewModel
import com.woowahan.accountbook.ui.navigate.ADD_INCOME
import com.woowahan.accountbook.ui.navigate.ADD_PAYMENTS
import com.woowahan.accountbook.ui.navigate.ADD_SPENDING
import com.woowahan.accountbook.ui.navigate.BottomNavItem
import com.woowahan.accountbook.ui.settings.SettingAddScreen
import com.woowahan.accountbook.ui.settings.SettingsAddViewModel
import com.woowahan.accountbook.ui.settings.SettingsScreen
import com.woowahan.accountbook.ui.settings.SettingsViewModel
import com.woowahan.accountbook.ui.theme.AccountBookTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()
    private val sharedViewModel by viewModels<SharedViewModel>()

    private val recordListViewModel by viewModels<RecordListViewModel>()
    private val recordAddViewModel by viewModels<RecordAddViewModel>()
    private val analysisViewModel by viewModels<AnalysisViewModel>()
    private val calendarViewModel by viewModels<CalendarViewModel>()
    private val settingsViewModel by viewModels<SettingsViewModel>()
    private val settingAddViewModel by viewModels<SettingsAddViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }

        mainViewModel.getRecords()
        settingsViewModel.getSettings()
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.ItemList.screenRoute
        ) {
            composable(BottomNavItem.ItemList.screenRoute) {
                RecordListScreen(
                    navController,
                    mainViewModel,
                    recordListViewModel,
                    sharedViewModel
                )
            }
            composable(BottomNavItem.Calendar.screenRoute) {
                CalendarScreen(mainViewModel, calendarViewModel)
            }
            composable(BottomNavItem.Analysis.screenRoute) {
                AnalysisScreen(mainViewModel, analysisViewModel)
            }
            composable(BottomNavItem.Settings.screenRoute) {
                SettingsScreen(navController = navController, settingsViewModel, sharedViewModel)
            }
            composable(BottomNavItem.AddRecordItem.screenRoute) {
                RecordAddScreen(
                    navController = navController,
                    recordAddViewModel,
                    sharedViewModel
                ) {
                    mainViewModel.getRecords()
                }
            }
            composable(BottomNavItem.AddPayments.screenRoute) {
                SettingAddScreen(
                    navController = navController,
                    ADD_PAYMENTS,
                    settingAddViewModel,
                    sharedViewModel
                ) {
                    mainViewModel.getRecords()
                    settingsViewModel.getSettings()
                }
            }
            composable(BottomNavItem.AddIncome.screenRoute) {
                SettingAddScreen(
                    navController = navController,
                    ADD_INCOME,
                    settingAddViewModel,
                    sharedViewModel
                ) {
                    mainViewModel.getRecords()
                    settingsViewModel.getSettings()
                }
            }
            composable(BottomNavItem.AddSpending.screenRoute) {
                SettingAddScreen(
                    navController = navController,
                    ADD_SPENDING,
                    settingAddViewModel,
                    sharedViewModel
                ) {
                    mainViewModel.getRecords()
                    settingsViewModel.getSettings()
                }
            }
        }
    }

    @Composable
    private fun MainScreen() {
        val navController = rememberNavController()

        AccountBookTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Scaffold(
                    bottomBar = {
                        BottomNaviBar(navController)
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        NavigationGraph(navController = navController)
                    }
                }
            }
        }
    }
}