package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.answer.Answer
import pl.michal_cyran.function_solver.function.domain.parameters.Domain
import pl.michal_cyran.function_solver.function.presentation.AnimatedDashedLines
import pl.michal_cyran.function_solver.function.presentation.FunctionGraph

@Composable
fun LeftPanel(
    answer: Answer?,
    function: Function,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
    ) {
//        AnimatedDashedLines()
        FunctionGraph(
            function = function,
            answer = answer,
            modifier = Modifier.weight(1f).fillMaxWidth(1f)
        )
    }
}