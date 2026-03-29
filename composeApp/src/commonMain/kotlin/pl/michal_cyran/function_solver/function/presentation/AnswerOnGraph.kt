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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.answer.Answer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersSet

data class ResolvedAnswerSegment(
    val startX: Float,
    val startY: Float,
    val endX: Float,
    val endY: Float,
    val startOnAxis: Boolean,   // startY == 0 (dla isX)
    val endOnAxis: Boolean,     // endY == 0 (dla isX)
)

data class ResolvedAnswer(
    val isX: Boolean,
    val color: Color,
    val segments: List<ResolvedAnswerSegment>,
    val pointsOnAxis: List<Float>,          // dla NumbersSet (isX, nie-interval)
    val dashIntervals: FloatArray,
)

@Composable
fun rememberResolvedAnswer(
    function: Function,
    answer: Answer?,
    minX: Int, maxX: Int,
    minY: Int, maxY: Int,
    canvasSize: Size,
    dashLength: Float = 20f,
    gapLength: Float = 40f,
): ResolvedAnswer? {
    return remember(answer, function, canvasSize) {
        if (answer == null || answer.numbersContainers.isEmpty()) return@remember null
        resolveAnswer(
            function, answer,
            minX, maxX, minY, maxY,
            canvasSize, dashLength, gapLength,
        )
    }
}

private fun resolveAnswer(
    function: Function,
    answer: Answer,
    minX: Int, maxX: Int,
    minY: Int, maxY: Int,
    canvasSize: Size,
    dashLength: Float,
    gapLength: Float,
): ResolvedAnswer {
    val color = if (answer.isX) Color(0xffFFA500) else Color.Green
    val W = canvasSize.width
    val H = canvasSize.height

    fun toCanvasX(v: Float) = (v - minX) / (maxX - minX) * W
    fun toCanvasY(v: Float) = H - (v - minY) / (maxY - minY) * H

    // Spłaszczone listy punktów – raz na answer
    val allPoints = function.intervals.flatMap { it.points }

    val segments = mutableListOf<ResolvedAnswerSegment>()
    val axisPoints = mutableListOf<Float>()

    if (answer.isX) {
        for (container in answer.numbersContainers) {
            when (container) {
                is NumbersSet -> {
                    container.numbers.forEach { x ->
                        axisPoints += toCanvasX(x)
                    }
                }
                is NumbersInterval -> {
                    val a = container.start
                    val b = container.end

                    // Priorytet: pasuje i inkludowanie, fallback: tylko x
                    val startPt = allPoints.firstOrNull { it.x == a && it.including == container.isStartIncluded }
                        ?: allPoints.first { it.x == a }

                    val endPt = allPoints.firstOrNull { it.x == b && it.including == container.isEndIncluded }
                        ?: allPoints.first { it.x == b }

                    segments += ResolvedAnswerSegment(
                        startX = toCanvasX(startPt.x),
                        startY = toCanvasY(startPt.y),
                        endX   = toCanvasX(endPt.x),
                        endY   = toCanvasY(endPt.y),
                        startOnAxis = startPt.y == 0f,
                        endOnAxis   = endPt.y == 0f,
                    )
                }
            }
        }
    } else {
        for (container in answer.numbersContainers) {
            if (container !is NumbersInterval) continue
            val a = container.start
            val b = container.end

            val startPt = allPoints.first { it.y == a }
            val endPt   = allPoints.last  { it.y == b }

            segments += ResolvedAnswerSegment(
                startX = toCanvasX(startPt.x),
                startY = toCanvasY(startPt.y),
                endX   = toCanvasX(endPt.x),
                endY   = toCanvasY(endPt.y),
                startOnAxis = false,
                endOnAxis   = false,
            )
        }
    }

    return ResolvedAnswer(
        isX          = answer.isX,
        color        = color,
        segments     = segments,
        pointsOnAxis = axisPoints,
        // FloatArray tworzony raz, nie w każdej klatce
        dashIntervals = floatArrayOf(dashLength, gapLength),
    )
}

fun DrawScope.drawResolvedAnswer(
    resolved: ResolvedAnswer,
    dashesAnimOffset: Float,
    circlesAnimRadius: Float,
) {
    val axisY   = size.height / 2f
    val axisX   = size.width  / 2f
    val stroke  = 5f

    val pathEffect = PathEffect.dashPathEffect(resolved.dashIntervals, dashesAnimOffset)

    if (resolved.isX) {
        // Animowane kółka dla NumbersSet
        resolved.pointsOnAxis.forEach { x ->
            drawCircle(
                color  = resolved.color,
                radius = circlesAnimRadius,
                center = Offset(x, axisY),
                style  = Stroke(width = stroke)
            )
        }

        // Przerywane linie dla NumbersInterval
        resolved.segments.forEach { seg ->
            if (seg.startOnAxis) {
                drawCircle(
                    color  = resolved.color,
                    radius = circlesAnimRadius,
                    center = Offset(seg.startX, axisY),
                    style  = Stroke(width = stroke)
                )
            }
            if (seg.endOnAxis) {
                drawCircle(
                    color  = resolved.color,
                    radius = circlesAnimRadius,
                    center = Offset(seg.endX, axisY),
                    style  = Stroke(width = stroke)
                )
            }

            drawLine(
                color       = resolved.color,
                start       = Offset(seg.startX, seg.startY),
                end         = Offset(seg.startX, axisY),
                strokeWidth = stroke,
                pathEffect  = pathEffect,
            )
            drawLine(
                color       = resolved.color,
                start       = Offset(seg.endX, seg.endY),
                end         = Offset(seg.endX, axisY),
                strokeWidth = stroke,
                pathEffect  = pathEffect,
            )
        }
    } else {
        resolved.segments.forEach { seg ->
            drawLine(
                color       = resolved.color,
                start       = Offset(seg.startX, seg.startY),
                end         = Offset(axisX, seg.startY),
                strokeWidth = stroke,
                pathEffect  = pathEffect,
            )
            drawLine(
                color       = resolved.color,
                start       = Offset(seg.endX, seg.endY),
                end         = Offset(axisX, seg.endY),
                strokeWidth = stroke,
                pathEffect  = pathEffect,
            )
        }
    }
}

