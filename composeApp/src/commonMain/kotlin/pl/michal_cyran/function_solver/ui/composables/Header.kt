package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    modifier: Modifier = Modifier,
    onGenerateFunction: () -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier.padding(8.dp).padding(horizontal = 100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Odczytywanie własności funkcji z wykresu",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier.weight(1f))

            Button(
                onClick = onGenerateFunction,
            ) {
                Text(text = "Wygeneruj nową funkcję")
            }
        }
    }
}