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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.skia.FontWeight
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.answer.Answer
import pl.michal_cyran.function_solver.theme.Colors
import kotlin.math.abs

@Composable
fun FunctionGraph(
    function: Function,
    answer: Answer? = null,
    modifier: Modifier = Modifier,
) {
    val textMeasurer = rememberTextMeasurer()

    val dashLength = 20f
    val gapLength = 40f
    val totalLength = dashLength + gapLength
    val dashesAnimOffset = remember { Animatable(0f) }
    val circlesAnimRadius = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            dashesAnimOffset.animateTo(
                targetValue = totalLength,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            dashesAnimOffset.snapTo(0f)
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            circlesAnimRadius.animateTo(
                targetValue = 10f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
            circlesAnimRadius.snapTo(0f)
        }
    }

    Canvas(
        modifier = modifier
    ) {
        drawRect(
            color = Colors.background,
            size = size,
        )
//        val minX = ((function.intervals.flatMap { it.points }.minByOrNull { it.x })?.x?.toInt() ?: -10) - 1
//        val maxX = ((function.intervals.flatMap { it.points }.maxByOrNull { it.x })?.x?.toInt() ?: 10) + 1
//
//        val minY = ((function.intervals.flatMap { it.points }.minByOrNull { it.y })?.y?.toInt() ?: -10) - 1
//        val maxY = ((function.intervals.flatMap { it.points }.maxByOrNull { it.y })?.y?.toInt() ?: 10) + 1

        val xFarthest = function.intervals.flatMap { it.points }.maxOfOrNull { abs(it.x) } ?: 5
        val minX = -xFarthest.toInt() - 1
        val maxX = xFarthest.toInt() + 1

        val yFarthest = function.intervals.flatMap { it.points }.maxOfOrNull { abs(it.y) } ?: 5
        val minY = -yFarthest.toInt() - 1
        val maxY = yFarthest.toInt() + 1
//        val minX = -8
//        val maxX = 8
//        val minY = -6
//        val maxY = 6

        drawGrid(minX = minX, maxX = maxX, minY = minY, maxY = maxY, size = size, textMeasurer = textMeasurer)

        val points = mutableListOf<Point>()
        function.intervals.forEach { interval ->
            interval.points.forEachIndexed { index, point ->
                val x = (point.x - minX) / (maxX - minX) * size.width
                val y = size.height - (point.y - minY) / (maxY - minY) * size.height

                points.add(Point(x, y, including = point.including))
            }
        }


        for (i in 0 until points.size - 1) {
            val start = points[i]
            val end = points[i + 1]

            drawLine(
                color = Colors.blue,
                start = Offset(start.x, start.y),
                end = Offset(end.x, end.y),
                strokeWidth = 4f
            )
        }
        drawPoint(points.first())
        drawPoint(points.last())

        answer?.let {
            answerOnGraph(
                function = function,
                answer = it,
                minX = minX,
                maxX = maxX,
                minY = minY,
                maxY = maxY,
                dashLength = dashLength,
                gapLength = gapLength,
                dashesAnimOffset = dashesAnimOffset,
                circlesAnimRadius = circlesAnimRadius,
            )
        }
    }
}

fun DrawScope.drawGrid(
    minX: Int,
    maxX: Int,
    minY: Int,
    maxY: Int,
    size: Size,
    textMeasurer: TextMeasurer,
) {
    val xLinesCount = maxX - minX
    val yLinesCount = maxY - minY


    for (i in 0..yLinesCount) {
        val yPosition = size.height / yLinesCount * i
        drawLine(
            color = Colors.gray,
            start = Offset(0f, yPosition),
            end = Offset(size.width, yPosition),
            strokeWidth = 2f
        )
    }

    for (i in 0..xLinesCount) {
        val xPosition = size.width / xLinesCount * i
        drawLine(
            color = Colors.gray,
            start = Offset(xPosition, 0f),
            end = Offset(xPosition, size.height),
            strokeWidth = 2f
        )
    }


    drawLine(
        color = Colors.gray,
        start = Offset(0f, size.height / 2),
        end = Offset(size.width, size.height / 2),
        strokeWidth = 5f
    )

    drawLine(
        color = Colors.gray,
        start = Offset(size.width / 2, 0f),
        end = Offset(size.width / 2, size.height),
        strokeWidth = 5f
    )


    for (i in minX..maxX) {
        val xPosition = size.width / (maxX - minX) * (i - minX) - 5f
        drawText(
            textMeasurer = textMeasurer,
            text = i.toString(),
            topLeft = Offset(xPosition, size.height / 2 + 10f),
            style = TextStyle(
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = Bold
            )
        )
    }


    for (i in minY..maxY) {
        val yPosition = (size.height / (maxY - minY) * (i - minY) + 5f).coerceIn(0f, size.height)
        drawText(
            textMeasurer = textMeasurer,
            text = (i * -1).toString(),
            topLeft = Offset(size.width / 2 - 20f, yPosition),
            style = TextStyle(
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = Bold
            )
        )
    }
}

fun DrawScope.drawPoint(
    point: Point,
) {
    val radius = 8f
    if (point.including) {
        drawCircle(
            color = Color.Cyan,
            radius = radius,
            center = Offset(point.x, point.y)
        )
    } else {
        drawCircle(
            color = Color.Cyan,
            radius = radius,
            center = Offset(point.x, point.y),
            style = Stroke(4f)
        )
    }
}

