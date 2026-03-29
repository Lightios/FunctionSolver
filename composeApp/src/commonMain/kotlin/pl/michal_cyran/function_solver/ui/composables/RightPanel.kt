package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.Parameters
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.theme.AppColors

@Composable
fun RightPanel(
    function: Function,
    onAnswerHover: (List<NumbersContainer>, Boolean) -> Unit,
    onAnswerUnhover: () -> Unit,
    userAnswers: Map<Parameters, String>,
    onAnswerChange: (Parameters, String) -> Unit,
    onCheckAnswer: (Parameters) -> Unit,
    isAnswerCorrect: Boolean? = null,
    checkedAnswer: Parameters?,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(modifier = modifier) {
        // Tab strip
        LabTabRow(
            selectedIndex = selectedTabIndex,
            tabs = listOf("WŁASNOŚCI", "ODPOWIEDZI"),
            onSelect = { selectedTabIndex = it }
        )

        Spacer(Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                when (selectedTabIndex) {
                    0 -> FunctionPropertiesPanel(
                        userAnswers = userAnswers,
                        onAnswerChange = onAnswerChange,
                        onCheckAnswer = onCheckAnswer,
                        isAnswerCorrect = isAnswerCorrect,
                        checkedAnswer = checkedAnswer,
                    )
                    1 -> AnswersPanel(
                        function = function,
                        onAnswerHover = onAnswerHover,
                        onAnswerUnhover = onAnswerUnhover,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun LabTabRow(
    selectedIndex: Int,
    tabs: List<String>,
    onSelect: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(AppColors.SurfaceL2)
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tabs.forEachIndexed { i, label ->
            val selected = i == selectedIndex
            val bg by animateColorAsState(
                if (selected) AppColors.Cyan else Color.Transparent,
                tween(200)
            )
            val textColor by animateColorAsState(
                if (selected) AppColors.Background else AppColors.TextSecondary,
                tween(200)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(bg)
                    .clickable { onSelect(i) }
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = label,
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        letterSpacing = 0.1.em,
                    )
                )
            }
        }
    }
}
