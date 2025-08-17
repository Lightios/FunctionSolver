import MonotonicityTest.Companion.sampleFunction
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.normalize
import pl.michal_cyran.function_solver.function.domain.parameters.Monotonicity
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class NormalizationTest {
    @Test
    fun `Matching end and start`() {
        val intervals = listOf(
            NumbersInterval(0f, 1f, true, false),
            NumbersInterval(1f, 2f, true, true)
        ).normalize()

        assertEquals(1, intervals.size)
    }

    @Test
    fun `Non-matching end and start`() {
        val intervals = listOf(
            NumbersInterval(0f, 1f, true, false),
            NumbersInterval(2f, 3f, true, true)
        ).normalize()

        assertEquals(2, intervals.size)
        assertContains(intervals, NumbersInterval(0f, 1f, true, false))
        assertContains(intervals, NumbersInterval(2f, 3f, true, true))
    }

    @Test
    fun `Multiple intervals merging`() {
        val intervals = listOf(
            NumbersInterval(0f, 1f, true, false),
            NumbersInterval(1f, 2f, true, true),
            NumbersInterval(2f, 3f, true, false)
        ).normalize()

        assertEquals(1, intervals.size)
        assertContains(intervals, NumbersInterval(0f, 3f, true, false))
    }


    @Test
    fun `Reversed order merging`() {
        val intervals = listOf(
            NumbersInterval(2f, 3f, true, false),
            NumbersInterval(1f, 2f, true, true),
        ).normalize()

        assertEquals(1, intervals.size)
    }
}