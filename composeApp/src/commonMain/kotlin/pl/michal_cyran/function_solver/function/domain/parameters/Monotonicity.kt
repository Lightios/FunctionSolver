package pl.michal_cyran.function_solver.function.domain.parameters

import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval

class Monotonicity(
    val function: Function
) {
    fun getAscending(): List<NumbersInterval> {
        return get { a, b ->
            a < b
        }
    }

    fun getDescending(): List<NumbersInterval> {
        return get { a, b ->
            a > b
        }
    }

    fun getConstant(): List<NumbersInterval> {
        return get { a, b ->
            a == b
        }
    }

    private fun get(
        compare: (Float, Float) -> Boolean
    ): List<NumbersInterval> {
        val result = mutableListOf<NumbersInterval>()

        for (interval in function.intervals) {
            var previousPoint = interval.points.first()
            val currentIntervalResults = mutableListOf<NumbersInterval>()

            for (i in 1..interval.points.lastIndex) {
                val currentPoint = interval.points[i]
                if (compare(previousPoint.y, currentPoint.y)) {
                    currentIntervalResults.add(
                        NumbersInterval(
                            previousPoint.x,
                            currentPoint.x,
                            previousPoint.including,
                            currentPoint.including
                        )
                    )
                }
                previousPoint = currentPoint
            }

            if (currentIntervalResults.isEmpty()) continue

            var previousSet = currentIntervalResults.first()

            for (i in 1..currentIntervalResults.lastIndex) {
                val currentSet = currentIntervalResults[i]

                val sumOfIntervals = previousSet + currentSet

                if (sumOfIntervals.size == 1) {
                    previousSet = sumOfIntervals.first()
                } else {
                    result.add(previousSet)
                    previousSet = currentSet
                }
            }

            result.add(previousSet)
        }

        return result
    }
}