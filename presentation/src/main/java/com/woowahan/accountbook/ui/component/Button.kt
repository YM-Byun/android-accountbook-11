package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.ui.theme.White
import com.woowahan.accountbook.ui.theme.Yellow

@Composable
fun LargeButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Yellow),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(
            text = text,
            color = White,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}