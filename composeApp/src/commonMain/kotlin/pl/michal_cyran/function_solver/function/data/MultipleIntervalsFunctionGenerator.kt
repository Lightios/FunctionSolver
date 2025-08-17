package pl.michal_cyran.function_solver.function.data

import kotlinx.coroutines.NonCancellable.start
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval


fun generateMultipleIntervalsFunction(): Function {
    val starts = mutableListOf<Int>()
    val pointsCounts = mutableListOf<Int>()

    val intervalsCount = (2..3).random()

    val intervals = mutableListOf<Interval>()

    for (i in 0 until intervalsCount) {
        val start = if (starts.isEmpty()) {
            (-6..-4).random()
        } else {
            starts.last() + pointsCounts.last() + (-1..1).random()
        }
        starts.add(start)


        pointsCounts.add((2..5).random())

        val interval = generateInterval(
            start = start,
            pointsCount = pointsCounts.last()
        )

        intervals.add(interval)
    }

    // fix double including ends
    for (i in 0 until intervals.size - 1) {
        val currentInterval = intervals[i]
        val nextInterval = intervals[i + 1]

        if (currentInterval.points.last().x == nextInterval.points.first().x) {
            val pickingList = mutableListOf(true, false, false)

            val isFirstIncluding = pickingList.random()
            pickingList.remove(isFirstIncluding)
            val isLastIncluding = pickingList.random()

            val newLast = currentInterval.points.last().copy(
                including = isLastIncluding
            )
            val newFirst = nextInterval.points.first().copy(
                including = isFirstIncluding
            )

            intervals[i] = currentInterval.copy(
                points = currentInterval.points.dropLast(1) + newLast
            )
            intervals[i + 1] = nextInterval.copy(
                points = listOf(newFirst) + nextInterval.points.drop(1)
            )
        }
    }

    return Function(intervals)
}