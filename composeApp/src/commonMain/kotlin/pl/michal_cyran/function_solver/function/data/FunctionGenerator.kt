package pl.michal_cyran.function_solver.function.data
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.parameters.XInterceptions
import kotlin.math.ceil
import kotlin.math.floor


fun generateFunction(): Function {
    val start = (4..6).random()
    val pointsCount = (6..10).random()
    var lastY = Float.NEGATIVE_INFINITY

    var lastStreak = 0
    var lastTendency = (-1..1).random() * listOf(2, 3, 4).random()

    val points = List(pointsCount) { index ->
        val x = (index.toFloat() * 2) - start

        val y = if (lastY == Float.NEGATIVE_INFINITY) {
            val new = (-3..3).random().toFloat()
            lastY = new
            new
        } else {
            if (lastStreak < 2) {
                lastStreak++
            } else {
                var newTendency = lastTendency
                while (newTendency == lastTendency) {
                    newTendency = (-1..1).random() * listOf(2, 3, 4).random()
                }
                lastTendency = newTendency
                lastStreak = 0
            }

            val newY = lastY + lastTendency

            if (newY * lastY > 0 || lastY == 0f) {
                lastY = newY
                newY
            } else {
                lastY = 0f
                0f
            }
        }


        val including = if (index in listOf(0, pointsCount - 1)) {
            listOf(true, false).random()
        } else {
            true
        }
        Point(x, y, including = including)
    }

    return Function(
        listOf(
            Interval(
                points
            )
        )
    )
}