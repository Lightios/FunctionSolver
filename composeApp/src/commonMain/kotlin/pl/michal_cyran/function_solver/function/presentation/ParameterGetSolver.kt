package pl.michal_cyran.function_solver.function.presentation

import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Parameters
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.parameters.Domain
import pl.michal_cyran.function_solver.function.domain.parameters.Monotonicity
import pl.michal_cyran.function_solver.function.domain.parameters.PositiveNegative
import pl.michal_cyran.function_solver.function.domain.parameters.ValueRange
import pl.michal_cyran.function_solver.function.domain.parameters.XInterceptions

fun Parameters.getSolver(): (Function) -> List<NumbersContainer> {
    return when (this) {
        Parameters.DOMAIN -> { function -> Domain(function).get() }
        Parameters.RANGE -> { function -> ValueRange(function).get() }
        Parameters.ASCENDING -> { function -> Monotonicity(function).getAscending() }
        Parameters.DESCENDING -> { function -> Monotonicity(function).getDescending() }
        Parameters.CONSTANT -> { function -> Monotonicity(function).getConstant() }
        Parameters.POSITIVE -> { function -> PositiveNegative(function).getPositive() }
        Parameters.NEGATIVE -> { function -> PositiveNegative(function).getNegative() }
        Parameters.POSITIVE_OR_ZERO -> { function -> PositiveNegative(function).getZeroOrPositive() }
        Parameters.NEGATIVE_OR_ZERO -> { function -> PositiveNegative(function).getZeroOrNegative() }
        Parameters.X_INTERCEPTS -> { function -> XInterceptions(function).get() }
    }
}