package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersInterval

@Composable
fun RightPanel(
    function: Function,
    onAnswerHover: (List<NumbersContainer>, Boolean) -> Unit,
    onAnswerUnhover: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth(1f).padding(8.dp),
//                divider = {},
//                indicator = { tabPositions ->
//                    androidx.compose.material3.TabRowDefaults.Indicator(
//                        modifier = Modifier.width(tabPositions[selectedTabIndex].width)
//                    )
//                }
            ){
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = { Text("Własności funkcji") },
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = { Text("Odpowiedzi") },
                )
            }
        }


        item {
            when (selectedTabIndex) {
                0 -> FunctionPropertiesPanel()
                1 -> AnswersPanel(
                    function = function,
                    onAnswerHover = onAnswerHover,
                    onAnswerUnhover = onAnswerUnhover,
                    modifier = Modifier.fillMaxWidth(1f).padding(8.dp)
                )
            }
        }
    }
}
