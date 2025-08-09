import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class NumbersSetAddingTest {

    @Test
    fun `Adding Matching Start-End Intervals`() {
        val interval1 = NumbersInterval(
            start = 1f,
            end = 3f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val interval2 = NumbersInterval(
            start = 3f,
            end = 4f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val result = interval1 + interval2

        assertEquals(1f, result.first().start)
        assertEquals(4f, result.first().end)
    }

    @Test
    fun `Adding Non-Matching Intervals`() {
        val interval1 = NumbersInterval(
            start = 1f,
            end = 2f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val interval2 = NumbersInterval(
            start = 3f,
            end = 4f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val result = interval1 + interval2

        assertEquals(2, result.size)
        assertContains(result, NumbersInterval(1f, 2f, true, true))
        assertContains(result, NumbersInterval(3f, 4f, true, true))
    }

    @Test
    fun `Adding Overlapping Intervals`() {
        val interval1 = NumbersInterval(
            start = 1f,
            end = 3f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val interval2 = NumbersInterval(
            start = 2f,
            end = 4f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val result = interval1 + interval2

        assertEquals(1, result.size)
        assertEquals(1f, result.first().start)
        assertEquals(4f, result.first().end)
    }

    @Test
    fun `Adding Intervals with Different Inclusion`() {
        val interval1 = NumbersInterval(
            start = 1f,
            end = 3f,
            isStartIncluded = true,
            isEndIncluded = false
        )

        val interval2 = NumbersInterval(
            start = 3f,
            end = 4f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val result = interval1 + interval2

        assertEquals(1, result.size)
        assertEquals(1f, result.first().start)
        assertEquals(4f, result.first().end)
        assertEquals(true, result.first().isStartIncluded)
        assertEquals(true, result.first().isEndIncluded)
    }

    @Test
    fun `Adding Intervals with Same Start and End`() {
        val interval1 = NumbersInterval(
            start = 1f,
            end = 3f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val interval2 = NumbersInterval(
            start = 1f,
            end = 3f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val result = interval1 + interval2

        assertEquals(1, result.size)
        assertEquals(1f, result.first().start)
        assertEquals(3f, result.first().end)
    }

    @Test
    fun `Adding Inclusive Intervals`() {
        val interval1 = NumbersInterval(
            start = 1f,
            end = 4f,
            isStartIncluded = true,
            isEndIncluded = false
        )

        val interval2 = NumbersInterval(
            start = 2f,
            end = 3f,
            isStartIncluded = true,
            isEndIncluded = true
        )

        val result = interval1 + interval2

        assertEquals(1, result.size)
        assertEquals(1f, result.first().start)
        assertEquals(4f, result.first().end)
        assertEquals(true, result.first().isStartIncluded)
        assertEquals(false, result.first().isEndIncluded)
    }
}