package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.chargemap.compose.numberpicker.NumberPicker
import com.woowahan.accountbook.ui.theme.White

@Composable
fun MonthPicker(
    currentYear: Int,
    currentMonth: Int,
    onDismissRequest: () -> Unit,
    onSelected: (Int, Int) -> Unit
) {
    var year by remember { mutableStateOf(currentYear) }
    var month by remember { mutableStateOf(currentMonth) }
    var endMonth = if (year == currentYear) {
        currentMonth
    } else {
        12
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
            color = White
        ) {
            Column(
                modifier = Modifier.padding(40.dp, 20.dp, 40.dp, 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NumberPicker(
                        value = year,
                        onValueChange = { year = it },
                        range = currentYear - 10..currentYear,
                        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp)
                    )
                    Text(text = "년", fontSize = 16.sp)
                    NumberPicker(
                        value = month,
                        onValueChange = { month = it },
                        range = 1..endMonth,
                        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp)
                    )
                    Text(text = "월", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(15.dp))

                LargeButton(
                    modifier = Modifier.padding(15.dp),
                    enabled = true,
                    text = "설정하기",
                    onClick = { onSelected(year, month) }
                )
            }
        }
    }
}