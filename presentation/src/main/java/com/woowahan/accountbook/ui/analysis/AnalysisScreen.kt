package com.woowahan.accountbook.ui.analysis

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.woowahan.accountbook.R
import com.woowahan.accountbook.ui.component.TopAppBar
import com.woowahan.accountbook.ui.main.MainViewModel
import com.woowahan.accountbook.ui.theme.Yellow4

@Composable
fun AnalysisScreen(
    mainViewModel: MainViewModel,
    analysisViewModel: AnalysisViewModel
) {
    val title by mainViewModel.appBarTitle.observeAsState("")
    analysisViewModel.getRecords(title)
    val pair = analysisViewModel.parseRecord()

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
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            AnimatedCircle(
                proportions = pair.first,
                colors = pair.second,
                modifier = Modifier
                    .size(254.dp)
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally)
            )
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