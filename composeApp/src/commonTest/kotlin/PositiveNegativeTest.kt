import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.parameters.PositiveNegative
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class PositiveNegativeTest {
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
    fun `Get positive`() {
        val positiveNegative = PositiveNegative(sampleFunction)
        val positiveIntervals = positiveNegative.getPositive()

        assertEquals(1, positiveIntervals.size)
        assertContains(positiveIntervals, NumbersInterval(0f, 2f, false, true))
    }

    @Test
    fun `Get positive or zero`() {
        val positiveNegative = PositiveNegative(sampleFunction)
        val positiveOrZeroIntervals = positiveNegative.getZeroOrPositive()

        assertEquals(1, positiveOrZeroIntervals.size)
        assertContains(positiveOrZeroIntervals, NumbersInterval(0f, 2f, true, true))
    }

}