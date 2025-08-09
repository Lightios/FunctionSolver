package pl.michal_cyran.function_solver.function.data

import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval
import pl.michal_cyran.function_solver.function.domain.Point

fun generateFunctionFromPoints(points: List<Point>): Function {
    return Function(
        listOf(
            Interval(
                points = points
            )
        )
    )
}