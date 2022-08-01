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

@Preview(showBackground = true)
@Composable
fun LightDivider() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            modifier = Modifier
                .height(1.dp),
            color = LightPurple
        )
    }
}