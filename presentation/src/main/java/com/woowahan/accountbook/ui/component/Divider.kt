package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowahan.accountbook.ui.theme.LightPurple
import com.woowahan.accountbook.ui.theme.Purple40

@Preview(showBackground = true)
@Composable
fun Purple40Divider() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .height(1.dp),
            color = Purple40
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LightPurpleDivider() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .height(1.dp),
            color = LightPurple
        )
    }
}