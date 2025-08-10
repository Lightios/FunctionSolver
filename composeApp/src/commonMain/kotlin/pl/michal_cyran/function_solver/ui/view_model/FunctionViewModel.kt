package pl.michal_cyran.function_solver.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.michal_cyran.function_solver.function.data.generateFunction
import pl.michal_cyran.function_solver.function.domain.Parameters
import pl.michal_cyran.function_solver.function.domain.answer.Answer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.presentation.getSolver

class FunctionViewModel: ViewModel() {
    private val _function =  MutableStateFlow(generateFunction())
    val function = _function.asStateFlow()

    private val _answer = MutableStateFlow<Answer?>(null)
    val answer = _answer.asStateFlow()

    private val _userAnswers = MutableStateFlow(
        mapOf(
            *Parameters.entries.map { it to "" }.toTypedArray()
        )
    )
    val userAnswers = _userAnswers.asStateFlow()

    private val _isAnswerCorrect = MutableStateFlow<Boolean?>(null)
    val isAnswerCorrect = _isAnswerCorrect.asStateFlow()

    private val _checkedAnswer = MutableStateFlow<Parameters?>(null)
    val checkedAnswer = _checkedAnswer.asStateFlow()


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

    fun setUserAnswer(property: Parameters, value: String) {
        _userAnswers.update { it + (property to value) }
    }

    fun checkAnswer(parameter: Parameters) {
        val correctAnswer = parameter.getSolver()(_function.value).toText().trim()
        val userAnswer = _userAnswers.value[parameter]?.trim() ?: ""

        if (correctAnswer == userAnswer) {
            _isAnswerCorrect.update { true }
        } else {
            _isAnswerCorrect.update { false }
        }

        _checkedAnswer.update { parameter }

        viewModelScope.launch {
            delay(2000)
            _isAnswerCorrect.update { null }
            _checkedAnswer.update { null }
        }
    }
}

fun List<NumbersContainer>.toText(): String {
    return joinToString(" ") { container ->
        when (container) {
            is NumbersInterval -> container.toString()
            else -> container.toString()
        }
    }
}