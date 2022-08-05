package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.ui.theme.White
import com.woowahan.accountbook.ui.theme.Yellow
import com.woowahan.accountbook.ui.theme.Yellow50

@Composable
fun LargeButton(
    modifier: Modifier,
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Yellow,
            disabledBackgroundColor = Yellow50
        ),
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