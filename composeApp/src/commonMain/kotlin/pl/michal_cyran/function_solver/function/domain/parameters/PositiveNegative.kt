package pl.michal_cyran.function_solver.function.domain.parameters

import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersSet
import pl.michal_cyran.function_solver.function.domain.numbers_set.normalize

class PositiveNegative(
    private val function: Function
) {
    fun getPositive() = ranges(orEqual = false) { it >= 0f }
    fun getNegative() = ranges(orEqual = false) { it <= 0f }
    fun getZeroOrPositive() = ranges(orEqual = true) { it >= 0f }
    fun getZeroOrNegative() = ranges(orEqual = true) { it <= 0f }

    private fun shouldInclude(
        point: Point,
        orEqual: Boolean,
    ): Boolean {
        if (!point.including)
            return false

        if (orEqual)
            return true

        return (point.y != 0f)
    }

    private fun skipLeadingAndFollowingZeros(list: List<Point>): List<Point> {
        val mutable = list.toMutableList()

        while (mutable.size > 1 && mutable[0].y == 0f && mutable[1].y == 0f) {
            mutable.removeAt(0)
        }

        while (mutable.size > 1 && mutable[mutable.lastIndex - 1].y == 0f && mutable[mutable.lastIndex].y == 0f) {
            mutable.removeAt(mutable.lastIndex)
        }

        return mutable.toList()
    }

    private fun transformToContainer(
        list: List<Point>,
        orEqual: Boolean,
    ): NumbersContainer? {
        if (list.isEmpty()) {
            return null
        }

        if (list.size == 1) {
            val point = list.first()
            val x = point.x

            return if (shouldInclude(point, orEqual))
                NumbersSet(x)
            else
                null
        }

        val newList = if (orEqual) list else skipLeadingAndFollowingZeros(list)

        val isStartIncluded = shouldInclude(newList.first(), orEqual)
        val isEndIncluded = shouldInclude(newList.last(), orEqual)

        return NumbersInterval(newList.first().x, newList.last().x, isStartIncluded, isEndIncluded)
    }

    private fun ranges(
        orEqual: Boolean,
        matches: (Float) -> Boolean,
    ): List<NumbersContainer> {
        val result = mutableListOf<NumbersContainer>()

        function.intervals.forEach { interval ->
            val current = mutableListOf<Point>()
            interval.points.forEach { point ->
                val matching = matches(point.y)
                val isZero = point.y == 0f

                if (matching) {
                    current.add(point)
                }

                if ((!matching) || (isZero && current.size > 1)) {
                    val newContainer = transformToContainer(current, orEqual)
                    if (newContainer != null) {
                        result.add(newContainer)
                    }
                    current.clear()

                    if (isZero) {
                        current.add(point)
                    }
                }
            }

            val newContainer = transformToContainer(current, orEqual)
            if (newContainer != null) {
                result.add(newContainer)
            }
            current.clear()
        }



        return result.normalize()
    }
}