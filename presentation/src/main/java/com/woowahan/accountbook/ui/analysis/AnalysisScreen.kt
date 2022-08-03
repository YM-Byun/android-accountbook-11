package com.woowahan.accountbook.ui.analysis

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.extenstion.month
import com.woowahan.accountbook.extenstion.year
import com.woowahan.accountbook.ui.component.BoldDivider
import com.woowahan.accountbook.ui.component.LightDivider
import com.woowahan.accountbook.ui.component.MonthPicker
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel
import com.woowahan.accountbook.ui.theme.Purple
import com.woowahan.accountbook.ui.theme.Red
import com.woowahan.accountbook.ui.theme.spendingColors

@Composable
fun AnalysisScreen(
    mainViewModel: MainViewModel,
    analysisViewModel: AnalysisViewModel
) {
    val title by mainViewModel.appBarTitle.observeAsState("")
    analysisViewModel.getRecords(title)
    val totalSpend by analysisViewModel.totalSpend.observeAsState(0L)
    val categoryList by analysisViewModel.categoryList.observeAsState()
    val ratioList by analysisViewModel.ratioList.observeAsState()
    var showPicker by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                btn1Image = R.drawable.ic_left,
                btn1OnClick = {
                    mainViewModel.onScreenChange("prev")
                },
                btn2Image = R.drawable.ic_right,
                btn2OnClick = {
                    mainViewModel.onScreenChange("next")
                },
                titleOnClick = {
                    showPicker = true
                }
            )
        }
    ) {
        Box {
            if (showPicker) {
                MonthPicker(
                    initYear = title.year(),
                    initMonth = title.month(),
                    onDismissRequest = { showPicker = false }) { nYear, nMonth ->
                    mainViewModel.onDatePicked(nYear, nMonth)
                    showPicker = false
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Row(modifier = Modifier.padding(16.dp, 10.dp, 16.dp, 10.dp)) {
                    Text(
                        text = "이번 달 총 지출 금액",
                        fontSize = 14.sp,
                        color = Purple,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = String.format("%,d", totalSpend),
                        fontSize = 14.sp,
                        color = Red,
                        fontWeight = FontWeight.Bold
                    )
                }
                BoldDivider()
                Spacer(modifier = Modifier.height(40.dp))
                AnimatedCircle(
                    proportions = ratioList!!.map { it.second },
                    colors = categoryList!!.map { spendingColors[it.color] },
                    modifier = Modifier
                        .size(254.dp)
                        .padding(20.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(40.dp))
                LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                    item {
                        categoryList!!.forEachIndexed { index, category ->
                            AnalysisCategoryText(
                                category = category,
                                amount = ratioList!![index].first,
                                ratio = (ratioList!![index].second * 100).toInt()
                            )
                            if (index != categoryList!!.lastIndex) {
                                LightDivider(padding = 16)
                            }
                        }
                    }
                }

                BoldDivider()
            }
        }
    }
}

@Composable
fun AnimatedCircle(
    proportions: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    val currentState = remember {
        MutableTransitionState(AnimatedCircleProgress.START)
            .apply { targetState = AnimatedCircleProgress.END }
    }
    val stroke = Stroke(150.dp.value)
    val transition = updateTransition(currentState, label = "")
    val angleOffset by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 500,
                durationMillis = 900,
                easing = LinearOutSlowInEasing
            )
        }, label = ""
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            360f
        }
    }
    val shift by transition.animateFloat(
        transitionSpec = {
            tween(
                delayMillis = 500,
                durationMillis = 900,
                easing = CubicBezierEasing(0f, 0.75f, 0.35f, 0.85f)
            )
        }, label = ""
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            30f
        }
    }

    Canvas(modifier) {
        val innerRadius = (size.minDimension - 5.dp.value) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
            halfSize.width - innerRadius,
            halfSize.height - innerRadius
        )
        val size = Size(innerRadius * 2, innerRadius * 2)
        var startAngle = shift - 90f
        proportions.forEachIndexed { index, proportion ->
            val sweep = proportion * angleOffset
            drawArc(
                color = colors[index],
                startAngle = startAngle + 1.8f / 2,
                sweepAngle = sweep - 1.8f,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = stroke
            )
            startAngle += sweep
        }
    }
}

private enum class AnimatedCircleProgress { START, END }