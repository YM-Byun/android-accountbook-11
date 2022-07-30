package com.woowahan.accountbook.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.ui.theme.Purple

@Composable
fun TopAppBar(
    title: String,
    btn1Image: Int?,
    btn1OnClick: () -> Unit,
    btn2Image: Int?,
    btn2OnClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                btn1Image?.let {
                    IconButton(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        onClick = btn1OnClick,
                        enabled = true,
                    ) {
                        Icon(
                            painter = painterResource(id = btn1Image),
                            contentDescription = "Back",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    fontSize = 18.sp,
                    color = Purple,
                    text = title
                )
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                btn2Image?.let {
                    IconButton(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        onClick = btn2OnClick,
                        enabled = true,
                    ) {
                        Icon(
                            painter = painterResource(id = btn2Image),
                            contentDescription = "btn2",
                            tint = Color.Unspecified
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Bottom
            ) {
                Divider(color = Purple, thickness = 1.dp)
            }
        }
    }
}