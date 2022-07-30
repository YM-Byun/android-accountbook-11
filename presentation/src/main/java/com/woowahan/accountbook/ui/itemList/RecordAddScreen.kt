package com.woowahan.accountbook.ui.itemList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.TopAppBar

@Preview
@Composable
fun RecordAddScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "내역 등록",
                btn1Image = R.drawable.ic_back,
                btn1OnClick = {},
                btn2Image = null,
                btn2OnClick = {}
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            FilterButton(
                showCheckBox = true,
                isLeftChecked = false,
                isRightChecked = false,
                leftText = "수입",
                rightText = "지출",
                modifier = Modifier.padding(16.dp),
                leftOnClick = {
                },
                rightOnClick = {

                }
            )
        }
    }
}