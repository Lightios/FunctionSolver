package pl.michal_cyran.function_solver.function.presentation.numbers_set

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersSet

@Composable
fun NumbersSetComposable(
    numbersSet: NumbersSet,
    modifier: Modifier = Modifier
) {
    Row {
        val string = "{" + numbersSet.numbers.joinToString(separator = ", ") { it.toInt().toString() } + "}"

        Text(
            text = string,
            modifier = modifier,
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge
        )
    }
}