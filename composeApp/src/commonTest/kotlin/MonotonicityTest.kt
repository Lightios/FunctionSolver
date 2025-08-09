import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.parameters.Monotonicity
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class MonotonicityTest {
    companion object {
        val sampleFunction = Function(
            intervals = listOf(
                Interval(
                    points = listOf(
                        Point(0f, 0f, including = true),
                        Point(1f, 1f, including = true),
                        Point(2f, 2f, including = true)
                    )
                ),
            )
        )
    }

    @Test
    fun testGetAscending() {
        val monotonicity = Monotonicity(sampleFunction)
        val ascendingIntervals = monotonicity.getAscending()

        assertEquals(1, ascendingIntervals.size)
        assertContains(ascendingIntervals, NumbersInterval(0f, 2f, true, true))
    }

    @Test
    fun `Ascending then Constant`() {
        val function = Function(
            intervals = listOf(
                Interval(
                    points = listOf(
                        Point(0f, 0f, including = true),
                        Point(1f, 1f, including = true),
                        Point(2f, 1f, including = true),
                    )
                ),
            )
        )
        val monotonicity = Monotonicity(function)
        val ascendingIntervals = monotonicity.getAscending()

        assertEquals(1, ascendingIntervals.size)
        assertContains(ascendingIntervals, NumbersInterval(0f, 1f, true, true))
    }

    @Test
    fun `Constant then Ascending`() {
        val function = Function(
            intervals = listOf(
                Interval(
                    points = listOf(
                        Point(0f, 1f, including = true),
                        Point(1f, 1f, including = true),
                        Point(2f, 2f, including = true),
                    )
                ),
            )
        )
        val monotonicity = Monotonicity(function)
        val ascendingIntervals = monotonicity.getAscending()

        assertEquals(1, ascendingIntervals.size)
        assertContains(ascendingIntervals, NumbersInterval(1f, 2f, true, true))
    }
}
