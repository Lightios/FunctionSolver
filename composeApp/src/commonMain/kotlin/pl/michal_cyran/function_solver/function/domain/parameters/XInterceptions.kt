package pl.michal_cyran.function_solver.function.domain.parameters

import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersSet

class XInterceptions(
        val function: Function
    ) {
    fun get(): List<NumbersContainer> {
        val points = mutableListOf<Float>()
        val intervals = mutableListOf<NumbersContainer>()

        for (interval in function.intervals) {
            for (i in 0 until interval.points.size - 1) {
                val point1 = interval.points[i]
                val point2 = interval.points[i + 1]

                val x1 = point1.x
                val x2 = point2.x
                val y1 = point1.y
                val y2 = point2.y

                if (y1 == 0f && y2 == 0f) {
                    intervals.add(
                        NumbersInterval(
                            start = x1,
                            end = x2,
                            isStartIncluded = point1.including,
                            isEndIncluded = point2.including
                        )
                    )
                    continue
                }

                if (y1 * y2 > 0) continue // Skip if both points are on the same side of the x-axis

                val a = (y2 - y1) / (x2 - x1)
                val b = y1 - a * x1
                val xIntercept = -b / a

                if (xIntercept == x1 && !point1.including) {
                    continue // Skip if the x-intercept is exactly at the first point and it's not included
                }

                if (xIntercept == x2 && !point2.including) {
                    continue // Skip if the x-intercept is exactly at the second point and it's not included
                }

                points.add(
                    xIntercept
                )
            }
        }

        val distinctPoints = points.distinct().sorted()
        var result: Any



        return result + NumbersSet(
            points
        )
    }
}
