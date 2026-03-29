package pl.michal_cyran.function_solver.function.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Function(
    val intervals: List<Interval>,
) {
    fun equals(other: Function): Boolean {
        intervals.forEachIndexed { index, interval ->
            val otherInterval = other.intervals.getOrNull(index) ?: return false
            if (interval.points.size != otherInterval.points.size) return false

            interval.points.forEachIndexed { pointIndex, point ->
                val otherPoint = otherInterval.points.getOrNull(pointIndex) ?: return false
                if (point.x != otherPoint.x || point.y != otherPoint.y) return false
            }
        }

        return true
    }

    constructor(vararg intervals: Interval): this(intervals.toList())
}