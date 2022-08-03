package com.woowahan.accountbook.ui.analysis

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.ui.component.RoundText
import com.woowahan.accountbook.ui.theme.Purple
import com.woowahan.accountbook.ui.theme.spendingColors
import com.woowahan.domain.model.Category

@Composable
fun AnalysisCategoryText(
    category: Category,
    amount: Long,
    ratio: Int
) {
    Row(
        modifier = Modifier.padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundText(text = category.name, color = spendingColors[category.color])
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = String.format("%,d", amount), fontSize = 14.sp, color = Purple)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "${ratio}%", fontSize = 14.sp, color = Purple, fontWeight = FontWeight.Bold)
    }
}