package pl.michal_cyran.function_solver.function.domain

import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.parameters.Domain
import pl.michal_cyran.function_solver.function.domain.parameters.Monotonicity
import pl.michal_cyran.function_solver.function.domain.parameters.PositiveNegative
import pl.michal_cyran.function_solver.function.domain.parameters.ValueRange

enum class Parameters(
    val displayPolishName: String,
    val isX: Boolean = true,
) {
    DOMAIN(
        "Dziedzina",
    ),
    RANGE(
        "Zbiór wartości",
        isX = false
    ),
    ASCENDING(
        "Przedział, w którym funkcja jest rosnąca",
    ),
    DESCENDING(
        "Przedział, w którym funkcja jest malejąca",
    ),
    CONSTANT(
        "Przedział, w którym funkcja jest stała",
    ),
    POSITIVE(
        "Przedział, w którym funkcja przyjmuje wartości dodatnie",
    ),
    NEGATIVE(
        "Przedział, w którym funkcja przyjmuje wartości ujemne",
    ),
    POSITIVE_OR_ZERO(
        "Przedział, w którym funkcja przyjmuje wartości większe lub równe od zera",
    ),
    NEGATIVE_OR_ZERO(
        "Przedział, w którym funkcja przyjmuje wartości mniejsze lub równe od zera",
    ),
    X_INTERCEPTS("Miejsca zerowe funkcji"),
}