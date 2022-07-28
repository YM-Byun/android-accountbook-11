package com.woowahan.accountbook.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.BottomNaviBar
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.settings.SettingsScreen
import com.woowahan.accountbook.ui.theme.AccountBookTheme

class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel)
        }
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
                ItemListScreen()
            }
            composable(BottomNavItem.Calendar.screenRoute) {
                CalendarScreen()
            }
            composable(BottomNavItem.Analysis.screenRoute) {
                AnalysisScreen()
            }
            composable(BottomNavItem.Settings.screenRoute) {
                SettingsScreen()
            }
        }
    }

    @Composable
    private fun MainScreen(viewModel: MainViewModel) {
        val navController = rememberNavController()
        val title: String by viewModel.currentScreen.observeAsState("")

        AccountBookTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = title,
                            btn1Image = R.drawable.ic_left,
                            btn1OnClick = {
                                viewModel.onScreenChange("prev")
                            },
                            btn2Image = R.drawable.ic_right,
                            btn2OnClick = {
                                viewModel.onScreenChange("next")
                            }
                        )
                    },
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