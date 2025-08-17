package pl.michal_cyran.function_solver.function.domain.parameters

import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.normalize

class ValueRange(
    val function: Function
) {
    fun get(): List<NumbersInterval> {
        val result = mutableListOf<NumbersInterval>()

        for (interval in function.intervals) {
            val start = interval.points.minOfOrNull { it.y } ?: Float.NaN
            val end = interval.points.maxOfOrNull { it.y } ?: Float.NaN

            val isStartIncluded = interval.points.filter { it.y == start }.any { it.including }
            val isEndIncluded = interval.points.filter { it.y == end }.any { it.including }

            result.add(
                NumbersInterval(
                    start,
                    end,
                    isStartIncluded,
                    isEndIncluded
                )
            )
        }

        return result.normalize()
    }
}