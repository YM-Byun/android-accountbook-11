package com.woowahan.accountbook.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.woowahan.data.entity.DBHelper
import com.woowahan.accountbook.ui.analysis.AnalysisScreen
import com.woowahan.accountbook.ui.calendar.CalendarScreen
import com.woowahan.accountbook.ui.component.BottomNaviBar
import com.woowahan.accountbook.ui.itemList.RecordAddScreen
import com.woowahan.accountbook.ui.itemList.RecordListScreen
import com.woowahan.accountbook.ui.navigate.BottomNavItem
import com.woowahan.accountbook.ui.settings.SettingsScreen
import com.woowahan.accountbook.ui.theme.AccountBookTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }

        DBHelper(this, "test.db", null, 1)
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen(viewModel)
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.ItemList.screenRoute
        ) {
            composable(BottomNavItem.ItemList.screenRoute) {
                RecordListScreen(navController, viewModel)
            }
            composable(BottomNavItem.Calendar.screenRoute) {
                CalendarScreen(viewModel)
            }
            composable(BottomNavItem.Analysis.screenRoute) {
                AnalysisScreen(viewModel)
            }
            composable(BottomNavItem.Settings.screenRoute) {
                SettingsScreen(context = this@MainActivity)
            }
            composable(BottomNavItem.AddItem.screenRoute) {
                RecordAddScreen(navController)
            }
        }
    }

    @Composable
    private fun MainScreen(viewModel: MainViewModel) {
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