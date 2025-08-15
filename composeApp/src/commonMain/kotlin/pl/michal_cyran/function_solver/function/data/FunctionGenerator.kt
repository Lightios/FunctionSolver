package pl.michal_cyran.function_solver.function.data
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Interval
import pl.michal_cyran.function_solver.function.domain.Point
import pl.michal_cyran.function_solver.function.domain.parameters.XInterceptions
import kotlin.math.ceil
import kotlin.math.floor




fun generateFunction(
    oneInterval: Boolean = false,
): Function {
    return if (oneInterval) {
        generateOneIntervalFunction()
    } else {
        generateMultipleIntervalsFunction()
    }
}