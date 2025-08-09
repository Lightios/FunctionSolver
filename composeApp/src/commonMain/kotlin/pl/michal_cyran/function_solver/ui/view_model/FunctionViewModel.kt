package pl.michal_cyran.function_solver.ui.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pl.michal_cyran.function_solver.function.data.generateFunction
import pl.michal_cyran.function_solver.function.domain.answer.Answer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval

class FunctionViewModel: ViewModel() {
    private val _function =  MutableStateFlow(generateFunction())
    val function = _function.asStateFlow()

    private val _answer = MutableStateFlow<Answer?>(null)
    val answer = _answer.asStateFlow()

    fun regenerateFunction() {
        _function.update { generateFunction() }
    }

    fun setAnswer(numbersIntervals: List<NumbersContainer>, isX: Boolean) {
        val newAnswer = Answer(
            numbersContainers = numbersIntervals,
            isX = isX
        )

        _answer.update { newAnswer }
    }

    fun clearAnswer() {
        _answer.update { null }
    }
}