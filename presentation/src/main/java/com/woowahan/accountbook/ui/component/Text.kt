package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.ui.theme.LightPurple
import com.woowahan.accountbook.ui.theme.Purple
import com.woowahan.accountbook.ui.theme.Purple40
import java.util.*

@Preview
@Composable
fun HeaderTextView(header: String = "test") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 20.dp, 16.dp, 5.dp)
    ) {
        Text(
            fontSize = 16.sp,
            text = header,
            color = LightPurple
        )
    }
}

@Composable
fun InputPriceItem(title: String, price: MutableState<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(80.dp),
            text = title,
            fontSize = 14.sp,
            color = Purple
        )
        BasicTextField(
            value = price.value,
            onValueChange = { price.value = it },
            decorationBox = {
                if (price.value.isEmpty()) {
                    Text(
                        text = "선택하세요",
                        fontSize = 14.sp,
                        color = LightPurple
                    )
                }
                it()
            },
            textStyle = LocalTextStyle.current.copy(
                color = Purple,
                fontWeight = FontWeight.Bold
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}


@Composable
fun InputDateItem(title: String, text: MutableState<String>) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.width(80.dp),
            text = title,
            fontSize = 14.sp,
            color = Purple
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    DatePicker(context) { year, month, day ->
                        text.value = "${year}년 ${month + 1}월 ${day}일"
                    }
                },
            text = if (text.value.isEmpty()) {
                "선택하세요"
            } else {
                text.value
            },
            color = if (text.value.isEmpty()) {
                LightPurple
            } else {
                Purple
            },
            fontWeight = if (text.value.isEmpty()) {
                FontWeight.Normal
            } else {
                FontWeight.Bold
            },
            fontSize = 14.sp
        )
    }
}
