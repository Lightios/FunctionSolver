package pl.michal_cyran.function_solver.function.presentation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.answer.Answer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersSet

fun DrawScope.answerOnGraph(
    function: Function,
    answer: Answer,
    minX: Int,
    maxX: Int,
    minY: Int,
    maxY: Int,
    dashLength: Float = 20f,
    gapLength: Float = 40f,
    dashesAnimOffset: Float,
    circlesAnimRadius: Float,
) {
    if (answer.numbersContainers.isEmpty()) return
    val answerColor = if (answer.isX) Color(0xffFFA500) else Color.Green


    if (answer.isX) {
        for (numbersSet in answer.numbersContainers) {
            if (numbersSet !is NumbersInterval) {
                if (numbersSet is NumbersSet) {
                    numbersSet.numbers.forEach {
                        val x = (it - minX) / (maxX - minX) * size.width
                        val y = size.height - (0f - minY) / (maxY - minY) * size.height
                        drawCircle(
                            color = answerColor,
                            radius = circlesAnimRadius,
                            center = Offset(x, y),
                            style = Stroke(width = 5f)
                        )
                    }
                }
                return
            }
            val a = numbersSet.start
            val b = numbersSet.end

            val startingPoint = (function.intervals.flatMap {
                it.points.filter { point -> point.x == a && point.including == numbersSet.isStartIncluded }
            } + function.intervals.flatMap {
                it.points.filter { point -> point.x == a }
            }).first()

            val endingPoint = (function.intervals.flatMap {
                it.points.filter { point -> point.x == b && point.including == numbersSet.isEndIncluded }
            } + function.intervals.flatMap {
                it.points.filter { point -> point.x == b }
            }).first()

            val startingX = (startingPoint.x - minX) / (maxX - minX) * size.width
            val startingY = size.height - (startingPoint.y - minY) / (maxY - minY) * size.height

            val endingX = (endingPoint.x - minX) / (maxX - minX) * size.width
            val endingY = size.height - (endingPoint.y - minY) / (maxY - minY) * size.height

            if (startingPoint.y == 0f) {
                drawCircle(
                    color = answerColor,
                    radius = circlesAnimRadius,
                    center = Offset(startingX, size.height / 2),
                    style = Stroke(width = 5f)
                )
            }
            if (endingPoint.y == 0f) {
                drawCircle(
                    color = answerColor,
                    radius = circlesAnimRadius,
                    center = Offset(endingX, size.height / 2),
                    style = Stroke(width = 5f)
                )
            }

            drawLine(
                color = answerColor,
                start = Offset(startingX, startingY),
                end = Offset(startingX, size.height / 2),
                strokeWidth = 5f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), dashesAnimOffset)
            )

            drawLine(
                color = answerColor,
                start = Offset(endingX, endingY),
                end = Offset(endingX, size.height / 2),
                strokeWidth = 5f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), dashesAnimOffset)
            )

//            drawLine(
//                color = answerColor,
//                start = Offset(startingX, startingY),
//                end = Offset(endingX, endingY),
//                strokeWidth = 5f,
//                pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), dashesAnimOffset.value)
//            )
        }
    } else {
        for (numbersSet in answer.numbersContainers) {
            if (numbersSet !is NumbersInterval) continue
            val a = numbersSet.start
            val b = numbersSet.end

            val startingPoint = function.intervals.flatMap {
                it.points.filter { point -> point.y == a }
            }.first()

            val endingPoint = function.intervals.flatMap {
                it.points.filter { point -> point.y == b }
            }.last()

            val startingX = (startingPoint.x - minX) / (maxX - minX) * size.width
            val startingY = size.height - (startingPoint.y - minY) / (maxY - minY) * size.height

            val endingX = (endingPoint.x - minX) / (maxX - minX) * size.width
            val endingY = size.height - (endingPoint.y - minY) / (maxY - minY) * size.height

            drawLine(
                color = answerColor,
                start = Offset(startingX, startingY),
                end = Offset(size.width / 2, startingY),
                strokeWidth = 5f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), dashesAnimOffset)
            )

            drawLine(
                color = answerColor,
                start = Offset(endingX, endingY),
                end = Offset(size.width / 2, endingY),
                strokeWidth = 5f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), dashesAnimOffset)
            )
        }
    }


}

@Composable
fun AnimatedDashedLines() {
    val dashLength = 20f
    val gapLength = 40f
    val totalLength = dashLength + gapLength

    val animOffset = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            animOffset.animateTo(
                targetValue = totalLength,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            animOffset.snapTo(0f)
        }
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val lineCount = (size.height / totalLength).toInt() + 2
        for (i in 0 until lineCount) {
            val y = i * totalLength
            if (y > size.height) continue
            drawLine(
                color = Color.Red,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 4.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashLength, gapLength), animOffset.value)
            )
        }
    }

}