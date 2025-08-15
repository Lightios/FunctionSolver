package pl.michal_cyran.function_solver.function.data

import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval
import pl.michal_cyran.function_solver.function.domain.Point
import kotlin.ranges.random

fun generateOneIntervalFunction(): Function {
    val interval = generateInterval(
        start = (-6..-4).random(),
        pointsCount = (6..10).random()
    )

    return Function(
        listOf(
            interval
        )
    )
}