package pl.michal_cyran.function_solver.function.data
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.parameters.XInterceptions
import kotlin.math.ceil
import kotlin.math.floor




fun generateFunction(
    continuous: Boolean = true,
): Function {
    return if (continuous) {
        generateOneIntervalFunction()
    } else {
        generateMultipleIntervalsFunction()
    }
}