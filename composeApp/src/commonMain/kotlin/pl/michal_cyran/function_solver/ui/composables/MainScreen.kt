package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.michal_cyran.function_solver.ui.view_model.FunctionViewModel


@Composable
fun MainScreen() {
    val viewModel = viewModel<FunctionViewModel>()
    val function by viewModel.function.collectAsStateWithLifecycle()
    val answer by viewModel.answer.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().background(
            MaterialTheme.colorScheme.background
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = Modifier.fillMaxWidth(1f),
            onGenerateFunction = {
                viewModel.regenerateFunction()
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            LeftPanel(
                modifier = Modifier.weight(2f),
                function = function,
                answer = answer,
            )
            RightPanel(
                modifier = Modifier.weight(1f),
                function = function,
                onAnswerHover = viewModel::setAnswer,
                onAnswerUnhover = viewModel::clearAnswer,
            )
        }
    }
}