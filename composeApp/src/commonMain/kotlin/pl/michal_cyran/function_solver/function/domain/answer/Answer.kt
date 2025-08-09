package pl.michal_cyran.function_solver.function.domain.answer

import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval

data class Answer (
    val numbersContainers: List<NumbersContainer>,
    val isX: Boolean = true,
)
