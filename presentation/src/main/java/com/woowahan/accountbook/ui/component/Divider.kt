package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowahan.accountbook.ui.theme.LightPurple
import com.woowahan.accountbook.ui.theme.Purple40

@Composable
fun LightDivider(padding: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .height(1.dp)
                .padding(horizontal = padding.dp),
            color = Purple40
        )
    }
}

@Composable
fun BoldDivider() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .height(1.dp),
            color = LightPurple
        )
    }
}