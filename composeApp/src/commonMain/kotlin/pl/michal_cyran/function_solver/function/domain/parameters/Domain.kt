package pl.michal_cyran.function_solver.function.domain.parameters

import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.normalize

class Domain(
    val function: Function
) {
    fun get(): List<NumbersInterval> {
        val result = mutableListOf<NumbersInterval>()

        for (interval in function.intervals) {
            val start = interval.points.firstOrNull()?.x ?: Float.NaN
            val end = interval.points.lastOrNull()?.x ?: Float.NaN

            if (start.isNaN() || end.isNaN()) continue

            val isStartIncluded = interval.points.firstOrNull()?.including ?: false
            val isEndIncluded = interval.points.lastOrNull()?.including ?: false

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