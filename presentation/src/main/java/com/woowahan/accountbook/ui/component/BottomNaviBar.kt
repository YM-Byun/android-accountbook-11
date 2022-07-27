package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.theme.OffWhite
import com.woowahan.accountbook.ui.theme.Purple
import com.woowahan.accountbook.ui.theme.White

@Preview
@Composable
fun BottomNaviBar() {
    val items = arrayOf(
        Pair(R.drawable.ic_document_list, "내역"),
        Pair(R.drawable.ic_calendar_month, "달력"),
        Pair(R.drawable.ic_graph, "통계"),
        Pair(R.drawable.ic_settings, "설정")
    )

    BottomNavigation(
        backgroundColor = Purple,
        contentColor = OffWhite,
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.first), contentDescription = null) },
                label = { Text(item.second) },
                onClick = { },
                selected = false
            )
        }
    }
}