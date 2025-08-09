package pl.michal_cyran.function_solver.function.domain.parameters

import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersSet

class PositiveNegative(
    val function: Function
) {
    private var start: Float? = null
    private var end: Float? = null
    private var isStartIncluded: Boolean = false
    private var isEndIncluded: Boolean = false
    private var lastMatching: Float? = null
    private var result: MutableList<NumbersContainer> = mutableListOf()

    fun getPositive(): List<NumbersContainer> {
        return get(
            compare = { a, b -> a > b },
            orEqual = false
        )
    }

    fun getNegative(): List<NumbersContainer> {
        return get(
            compare = { a, b -> a < b },
            orEqual = false
        )
    }

    fun getZeroOrPositive(): List<NumbersContainer> {
        return get(
            compare = { a, b -> a >= b },
            orEqual = true
        )
    }

    fun getZeroOrNegative(): List<NumbersContainer> {
        return get(
            compare = { a, b -> a <= b },
            orEqual = true
        )
    }


    fun get(
        compare: (Float, Float) -> Boolean,
        orEqual: Boolean,
    ): List<NumbersContainer> {
        val compareOrEqual = { a: Float, b: Float -> compare(a, b) || a == b }

        for (interval in function.intervals) {
            for (i in 0 until interval.points.size) {
                val point = interval.points[i]
                val y = point.y

                if (compareOrEqual(y, 0f)) {
                    val isStrictlyMatching = compare(y, 0f)
                    addPointToInterval(point, isStrictlyMatching)
                } else {
                    breakCurrentInterval()
                }
            }

            breakCurrentInterval()
        }

        return result
    }

    private fun addPointToInterval(point: Point, isStrictlyMatching: Boolean) {
        if (start == null) {
            start = point.x
            isStartIncluded = point.including && isStrictlyMatching
        } else {
            end = point.x
            isEndIncluded = point.including && isStrictlyMatching
        }
        lastMatching = point.x
    }

    private fun breakCurrentInterval() {
        // 1 point matching
        if (start != null && end == null) {
            if (isStartIncluded) {
                result.add(
                    NumbersSet(
                        listOf(start!!),
                    )
                )
            }
        }

        // 2 points matching
        if (start != null && end != null) {
            result.add(
                NumbersInterval(
                    start = start!!,
                    end = end!!,
                    isStartIncluded = isStartIncluded,
                    isEndIncluded = isEndIncluded
                )
            )
        }

        start = null
        end = null
    }
}