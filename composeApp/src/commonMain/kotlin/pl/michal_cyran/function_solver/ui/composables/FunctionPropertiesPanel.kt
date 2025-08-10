package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.michal_cyran.function_solver.function.domain.Parameters
import pl.michal_cyran.function_solver.ui.composables.reusable.FunctionProperty
import kotlin.collections.getValue
import kotlin.collections.setValue

@Composable
fun FunctionPropertiesPanel(
    modifier: Modifier = Modifier,
    userAnswers: Map<Parameters, String>,
    onAnswerChange: (Parameters, String) -> Unit,
    onCheckAnswer: (Parameters) -> Unit,
    checkedAnswer: Parameters? = null,
    isAnswerCorrect: Boolean? = null,
) {
    var answers by rememberSaveable { mutableStateOf(
        mapOf(
            *Parameters.entries.map { it to "" }.toTypedArray()
        )
    ) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(20.dp)
        ) {

            Text(
                text = "Własności funkcji",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleLarge
            )
            for (parameter in Parameters.entries) {
                FunctionProperty(
                    propertyName = parameter.displayPolishName,
                    value = userAnswers[parameter] ?: "",
                    onValueChange = { newValue ->
                        onAnswerChange(
                            parameter,
                            newValue
                        )
                    },
                    onCheckAnswer = {
                        onCheckAnswer(parameter)
                    },
                    isAnswerCorrect = isAnswerCorrect,
                    isThisChecked = checkedAnswer == parameter,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }
        }
    }
}