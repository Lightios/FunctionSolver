package pl.michal_cyran.function_solver.function.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Interval (
    val points: List<Point>,
) {
    constructor(vararg points: Point): this(points.toList())
}