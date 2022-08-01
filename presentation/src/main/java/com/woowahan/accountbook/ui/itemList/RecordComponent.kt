package com.woowahan.accountbook.ui.itemList

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.RoundText
import com.woowahan.accountbook.ui.theme.*
import com.woowahan.data.entity.DBHelper
import com.woowahan.domain.model.Category
import com.woowahan.domain.model.Payment

@Composable
fun RecordHeader(
    header: String,
    income: Long,
    spending: Long,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 15.dp, 0.dp, 5.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = header,
            color = LightPurple,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        if (income != 0L) {
            Text(
                text = "수입",
                color = LightPurple,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = String.format("%,d", income),
                color = LightPurple,
                fontSize = 10.sp
            )
        }
        if (spending != 0L) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "지출",
                color = LightPurple,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "23424",
                color = LightPurple,
                fontSize = 10.sp
            )
        }

    }
}

@ExperimentalFoundationApi
@Composable
fun RecordItem(
    recordType: String,
    paymentType: String,
    content: String,
    price: Long,
    category: Category,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    isSelected: Boolean
) {
    val backgroundModifier = Modifier
        .fillMaxWidth()
        .combinedClickable(
            onClick = onClick,
            onLongClick = onLongClick
        )

    if (isSelected) {
        backgroundModifier.background(White)
    }

    Column(
        modifier = backgroundModifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isSelected) {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    painter = painterResource(id = R.drawable.ic_checkbox_checked),
                    contentDescription = "checked",
                    tint = Color.Unspecified
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val color = if (recordType == DBHelper.INCOME) {
                        incomeColors[category.color]
                    } else {
                        spendingColors[category.color]
                    }
                    RoundText(text = category.name, color = color)

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
                        text = content
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        fontSize = 14.sp,
                        color = if (price < 0) {
                            Red
                        } else {
                            Green6
                        },
                        fontWeight = FontWeight.Bold,
                        text = String.format("%,d", price) + "원"
                    )
                }
            }
        }
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
fun InputTextItem(title: String, content: MutableState<String>) {
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
            value = content.value,
            onValueChange = { content.value = it },
            decorationBox = {
                if (content.value.isEmpty()) {
                    Text(
                        text = "선택하세요",
                        fontSize = 14.sp,
                        color = LightPurple,
                        fontWeight = FontWeight.Normal
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
fun InputPaymentSpinnerItem(
    title: String,
    list: List<Payment>,
    onValueSelected: (Payment) -> Unit
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
                onValueSelected(list[currentItem.value])
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = list[currentItem.value].name,
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

            MaterialTheme(
                shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(16.dp)),
            ) {
                DropdownMenu(
                    modifier = Modifier.border(1.dp, Purple, RoundedCornerShape(16.dp)),
                    expanded = isClicked.value,
                    onDismissRequest = { isClicked.value = false },
                ) {
                    list.forEach {
                        DropdownMenuItem(onClick = {
                            isClicked.value = false
                            currentItem.value = list.indexOf(it)
                        }) {
                            Text(
                                text = it.name,
                                fontSize = 12.sp,
                                color = Purple
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputCategorySpinnerItem(
    title: String,
    list: List<Category>,
    onValueSelected: (Category) -> Unit
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
                onValueSelected(list[currentItem.value])
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = list[currentItem.value].name,
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

            MaterialTheme(
                shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(16.dp)),
            ) {
                DropdownMenu(
                    modifier = Modifier.border(1.dp, Purple, RoundedCornerShape(16.dp)),
                    expanded = isClicked.value,
                    onDismissRequest = { isClicked.value = false },
                ) {
                    list.forEach {
                        DropdownMenuItem(onClick = {
                            isClicked.value = false
                            currentItem.value = list.indexOf(it)
                        }) {
                            Text(
                                text = it.name,
                                fontSize = 12.sp,
                                color = Purple
                            )
                        }
                    }
                }
            }
        }
    }
}