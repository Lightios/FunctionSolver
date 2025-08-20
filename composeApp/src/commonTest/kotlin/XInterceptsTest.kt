import pl.michal_cyran.function_solver.function.data.generateFunctionFromPoints
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersSet
import pl.michal_cyran.function_solver.function.domain.parameters.XInterceptions
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class XInterceptsTest {
    @Test
    fun `X Intercepts with Single Interval`() {
        val function = generateFunctionFromPoints(
            listOf(
                Point(0f, -1f),
                Point(1f, 0f),
                Point(2f, 1f)
            )
        )
        val xInterceptions = XInterceptions(function).get()

        assertEquals(1, xInterceptions.size)
        assertContains(xInterceptions, NumbersSet(listOf(1f)))
    }

    @Test
    fun `X Intercepts with Multiple Intervals`() {
        val function = generateFunctionFromPoints(
            listOf(
                Point(0f, -1f),
                Point(1f, 0f),
                Point(2f, 1f),
                Point(3f, 0f),
                Point(4f, 2f)
            )
        )
        val xInterceptions = XInterceptions(function).get()

        assertEquals(2, xInterceptions.size)
        assertContains(xInterceptions, NumbersSet(listOf(1f)))
        assertContains(xInterceptions, NumbersSet(listOf(3f)))
    }

    @Test
    fun `X Intercepts with No Intercepts`() {
        val function = generateFunctionFromPoints(
            listOf(
                Point(0f, 1f),
                Point(1f, 2f),
                Point(2f, 3f)
            )
        )
        val xInterceptions = XInterceptions(function).get()

        assertEquals(0, xInterceptions.size)
    }

    @Test
    fun `Not including X intercepts on the ends`() {
        val function = Function(
            listOf(
                Interval(
                    listOf(
                        Point(0f, 0f, including = false),
                        Point(1f, 1f, including = true),
                        Point(2f, 0f, including = false),
                    )
                )
            )
        )

        val xInterceptions = XInterceptions(function).get()

        xInterceptions.forEach {
            if (it is NumbersSet) {
                assertEquals(1, it.numbers.size)
            } else {
                assertEquals(expected = true, actual = false)
            }
        }
    }

    @Test
    fun `Constant function at 0 value`() {
        val function = generateFunctionFromPoints(
            listOf(
                Point(0f, 0f),
                Point(1f, 0f),
                Point(2f, 0f)
            )
        )

        val xInterceptions = XInterceptions(function).get()
        assertEquals(1, xInterceptions.size)
        assertContains(xInterceptions, NumbersInterval(0f, 2f,
            isStartIncluded = true,
            isEndIncluded = true
        ))
    }
}
