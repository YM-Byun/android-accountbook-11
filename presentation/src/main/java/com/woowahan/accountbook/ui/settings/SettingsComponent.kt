package com.woowahan.accountbook.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.ui.component.RoundText
import com.woowahan.accountbook.ui.theme.LightPurple
import com.woowahan.accountbook.ui.theme.Purple
import com.woowahan.accountbook.ui.theme.Purple40
import com.woowahan.accountbook.ui.theme.Yellow4
import com.woowahan.accountbook.R

@Preview
@Composable
fun preview() {
    Column() {
        SettingsItemWithCategory(category = "test")
        SettingsItemWithNoCategory(text = "text")
        SettingsAddItem(text = "test") {
        }
    }
}

@Composable
fun SettingsItemWithCategory(
    category: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 10.dp, 10.dp, 0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category,
                color = Purple,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            RoundText(text = category, color = Yellow4)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Purple40
        )
    }
}

@Composable
fun SettingsItemWithNoCategory(
    text: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 13.dp, 10.dp, 0.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Purple,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(13.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Purple40
        )
    }
}

@Composable
fun SettingsAddItem(text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Purple,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "Add icon",
            )
        }
    }
}