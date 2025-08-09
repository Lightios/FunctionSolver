package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.michal_cyran.function_solver.function.domain.Parameters
import pl.michal_cyran.function_solver.ui.composables.reusable.FunctionProperty

@Composable
fun FunctionPropertiesPanel(
    modifier: Modifier = Modifier,
) {
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
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }
        }
    }
}