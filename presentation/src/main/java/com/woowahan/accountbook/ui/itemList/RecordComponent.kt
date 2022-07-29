package com.woowahan.accountbook.ui.itemList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.theme.*

@Composable
fun RecordItem(
    recordType: String,
    paymentType: String,
    title: String,
    amount: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 10.dp, 10.dp, 0.dp)
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
                color = if (amount.startsWith("-")) {
                    Red
                } else {
                    Green6
                },
                fontWeight = FontWeight.Bold,
                text = amount
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = LightPurple
        )
    }
}

@Composable
fun FloatingActionButton(
    onClick: () -> Unit
) {
    Box() {
        Button(
            modifier = Modifier.size(56.dp),
            onClick = onClick,
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(Yellow)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                tint = White,
                contentDescription = "plus button",
            )
        }
    }
}

@Composable
fun FilterButton(
    modifier: Modifier,
    showCheckBox: Boolean,
    isLeftChecked: Boolean,
    isRightChecked: Boolean,
    leftText: String,
    rightText: String,
    leftOnClick: () -> Unit,
    rightOnClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
            colors = if (isLeftChecked) {
                ButtonDefaults.buttonColors(Purple)
            } else {
                ButtonDefaults.buttonColors(LightPurple)
            },
            onClick = leftOnClick
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                if (showCheckBox) {
                    Checkbox(
                        modifier = Modifier.size(24.dp),
                        checked = isLeftChecked,
                        onCheckedChange = null,
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = Purple,
                            checkedColor = OffWhite,
                            uncheckedColor = OffWhite
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Text(
                    text = leftText,
                    color = OffWhite,
                    fontSize = 12.sp
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp),
            colors = if (isRightChecked) {
                ButtonDefaults.buttonColors(Purple)
            } else {
                ButtonDefaults.buttonColors(LightPurple)
            },
            onClick = rightOnClick
        ) {
            if (showCheckBox) {
                Checkbox(
                    modifier = Modifier.size(24.dp),
                    checked = isRightChecked,
                    onCheckedChange = null,
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Purple,
                        checkedColor = OffWhite,
                        uncheckedColor = OffWhite
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Text(
                text = rightText,
                color = OffWhite,
                fontSize = 12.sp
            )
        }
    }
}
