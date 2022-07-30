package com.woowahan.accountbook.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.HeaderTextView
import com.woowahan.accountbook.ui.component.LightDivider
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.itemList.InputTextItem
import com.woowahan.accountbook.ui.theme.*

@Preview
@Composable
fun SettingAddScreen() {
    var viewModel = remember { SettingsAddViewModel() }
    val mode = "spending"
    val colors = viewModel.getColors(mode)
    var selectedColor by viewModel.selectedColorIdx

    Scaffold(
        topBar = {
            TopAppBar(
                title = "내역 등록",
                btn1Image = R.drawable.ic_back,
                btn1OnClick = {
                    //navController.popBackStack()
                },
                btn2Image = null,
                btn2OnClick = {}
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                item {
                    InputTextItem(title = "이름", content = viewModel.name)
                    LightDivider()
                    Spacer(modifier = Modifier.height(10.dp))
                    HeaderTextView(header = "색상")
                    LightDivider()
                    Spacer(modifier = Modifier.height(10.dp))
                }
                colorPalette(colors) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                selectedColor = colors.indexOf(it)
                            }
                    ) {
                        val size = if (selectedColor == colors.indexOf(it)) {
                            24
                        } else {
                            20
                        }

                        Spacer(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(size.dp)
                                .background(it)
                        )
                    }
                }
            }
        }
    }
}

fun LazyListScope.colorPalette(
    colors: List<Color>,
    itemContent: @Composable BoxScope.(Color) -> Unit,
) {
    val rows = if (colors.isEmpty()) {
        0
    } else {
        1 + (colors.count() - 1) / 10
    }

    items(rows) { rowIdx ->
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (colIdx in 0 until 10) {
                val itemIdx = rowIdx * 10 + colIdx

                if (itemIdx < colors.count()) {
                    Box(
                        modifier = Modifier.weight(1f, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent.invoke(this, colors[itemIdx])
                    }
                } else {
                    Spacer(modifier = Modifier.weight(1f, fill = true))
                }
            }
        }
    }
}