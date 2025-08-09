import pl.michal_cyran.function_solver.function.data.generateFunctionFromPoints
import pl.michal_cyran.function_solver.function.domain.Point
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
        assertContains(xInterceptions, 1f)
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
        assertContains(xInterceptions, 1f)
        assertContains(xInterceptions, 3f)
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
}
