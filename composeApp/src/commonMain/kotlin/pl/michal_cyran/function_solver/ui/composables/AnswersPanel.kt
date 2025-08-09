package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Parameters
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.presentation.getSolver
import pl.michal_cyran.function_solver.ui.composables.reusable.Answer

@Composable
fun AnswersPanel(
    function: Function,
    onAnswerHover: (List<NumbersContainer>, Boolean) -> Unit,
    onAnswerUnhover: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(8.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(20.dp)
    ) {
        Parameters.entries.forEach { property ->
            val solution = property.getSolver()(function)
            Answer(
                propertyName = property.displayPolishName,
                value = solution,
                onHover = { onAnswerHover(solution, property.isX) },
                onUnhover = onAnswerUnhover,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }
    }
}