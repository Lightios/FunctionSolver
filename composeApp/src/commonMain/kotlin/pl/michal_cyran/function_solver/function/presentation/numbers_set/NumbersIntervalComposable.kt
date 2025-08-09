package pl.michal_cyran.function_solver.function.presentation.numbers_set

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval

@Composable
fun NumbersIntervalComposable(
    numbersInterval: NumbersInterval,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        val leftBracket = if (numbersInterval.isStartIncluded) {
            "["
        } else {
            "("
        }

        val rightBracket = if (numbersInterval.isEndIncluded) {
            "]"
        } else {
            ")"
        }

        Text(
            text = "$leftBracket${numbersInterval.start.toInt()}, ${numbersInterval.end.toInt()}$rightBracket",
            style = MaterialTheme.typography.titleLarge
        )
    }
}