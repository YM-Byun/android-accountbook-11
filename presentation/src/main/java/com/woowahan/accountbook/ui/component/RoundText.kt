package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.ui.theme.White

@Composable
fun RoundText(text: String, color: Color) {
    Text(
        modifier = Modifier
            .width(70.dp)
            .clip(CircleShape)
            .background(color)
            .padding(10.dp, 5.dp, 10.dp, 5.dp),
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 10.sp
    )
}