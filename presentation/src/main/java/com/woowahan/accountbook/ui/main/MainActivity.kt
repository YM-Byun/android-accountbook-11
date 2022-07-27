package com.woowahan.accountbook.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.BottomNaviBar
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.theme.AccountBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    private fun MainScreen() {
        AccountBookTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = "Good",
                            btn1Image = R.drawable.ic_left,
                            btn1OnClick = {
                                Toast.makeText(this, "btn1", Toast.LENGTH_SHORT).show()
                            },
                            btn2Image = R.drawable.ic_right,
                            btn2OnClick = {
                                Toast.makeText(this, "btn2", Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    bottomBar = {
                        BottomNaviBar()
                    }
                ) {

                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}