package com.woowahan.accountbook.ui.itemList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.theme.*
import com.woowahan.domain.model.Category

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

@Composable
fun InputTextItem(title: String, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Purple
        )
        Spacer(modifier = Modifier.width(30.dp))
        BasicTextField(
            value = text,
            onValueChange = {},
            decorationBox = {
                if (text.isEmpty()) {
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
            )
        )
    }
}

@Composable
fun InputSpinnerItem(
    title: String,
    list: List<String>
) {
    val isClicked = remember { mutableStateOf(false) }
    val currentItem = remember { mutableStateOf(-1) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Purple
        )
        Spacer(modifier = Modifier.width(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isClicked.value = !(isClicked.value)
                }
        ) {
            if (currentItem.value == -1) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = "선택하세요",
                    color = LightPurple,
                    fontSize = 14.sp,
                )
            } else {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = list[currentItem.value],
                    color = Purple,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                modifier = (if (isClicked.value) {
                    Modifier.rotate(180F)
                } else {
                    Modifier.rotate(0F)
                }).align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_variant13),
                contentDescription = "more",
                tint = LightPurple,
            )

            DropdownMenu(
                expanded = isClicked.value,
                onDismissRequest = { isClicked.value = false }
            ) {
                list.forEach {
                    DropdownMenuItem(onClick = {
                        isClicked.value = false
                        currentItem.value = list.indexOf(it)
                    }) {
                        Text(
                            text = it
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun InputPriceItem(title: String, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Purple
        )
        Spacer(modifier = Modifier.width(30.dp))
        BasicTextField(
            value = text,
            onValueChange = {},
            decorationBox = {
                if (text.isEmpty()) {
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
            )
        )
    }
}


@Composable
fun InputDateItem(title: String, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Purple
        )
        Spacer(modifier = Modifier.width(30.dp))
        BasicTextField(
            value = text,
            onValueChange = {},
            decorationBox = {
                if (text.isEmpty()) {
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
            )
        )
    }
}