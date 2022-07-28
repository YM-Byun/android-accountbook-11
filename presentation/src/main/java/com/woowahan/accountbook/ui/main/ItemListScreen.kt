package com.woowahan.accountbook.ui.main

import android.graphics.Paint
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.ui.theme.Pink4
import com.woowahan.accountbook.ui.theme.Purple
import com.woowahan.accountbook.ui.theme.Red
import com.woowahan.accountbook.ui.theme.Yellow4
import com.woowahan.domain.model.Item

@Composable
fun ItemListScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink4)
    ) {
        val dummies = getDummy()
        LazyColumn() {

        }
    }
}

@Composable
fun RecordItem(
    recordType: String,
    paymentType: String,
    title: String,
    amount: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .width(56.dp)
                    .clip(CircleShape)
                    .background(Yellow4)
                    .padding(10.dp, 5.dp, 10.dp, 5.dp),
                text = recordType,
                textAlign = TextAlign.Center,
                fontSize = 10.sp
            )

            Spacer(modifier = Modifier.weight(1f))
            Text(
                fontSize = 10.sp,
                color = Purple,
                text = paymentType
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                fontSize = 14.sp,
                color = Purple,
                fontWeight = FontWeight.Bold,
                text = title
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                fontSize = 14.sp,
                color = Red,
                fontWeight = FontWeight.Bold,
                text = amount
            )
        }
    }
}

fun getDummy(): ArrayList<Item> {
    val list = ArrayList<Item>()

    for (i in 0..10) {
        val dummy = Item("type", "title", "payment", "-10,000")
        list.add(dummy)
    }
    return list
}