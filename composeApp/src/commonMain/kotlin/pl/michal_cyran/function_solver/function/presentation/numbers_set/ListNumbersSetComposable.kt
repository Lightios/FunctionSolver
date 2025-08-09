package pl.michal_cyran.function_solver.function.presentation.numbers_set

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersSet

@Composable
fun ListNumbersSetComposable(
    numbersContainers: List<NumbersContainer>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        if (numbersContainers.isEmpty()) {
            Text(
                text = "Brak",
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            return@Row
        }

        numbersContainers.forEachIndexed { i, container ->

            when (container) {
                is NumbersInterval -> {
                    NumbersIntervalComposable(
                        numbersInterval = container,
                        modifier = modifier
                    )
                }

                is NumbersSet -> {
                    NumbersSetComposable(
                        numbersSet = container,
                        modifier = modifier
                    )
                }
            }

            if (i < numbersContainers.size - 1) {
                Text(
                    text = "∪",
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}