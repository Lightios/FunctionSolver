package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import pl.michal_cyran.function_solver.function.domain.Function
import pl.michal_cyran.function_solver.function.domain.answer.Answer
import pl.michal_cyran.function_solver.function.presentation.FunctionGraph
import pl.michal_cyran.function_solver.theme.AppColors

@Composable
fun LeftPanel(
    answer: Answer?,
    function: Function,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.SurfaceL1)
            .border(1.dp, AppColors.Border, RoundedCornerShape(12.dp))
    ) {
        FunctionGraph(
            function = function,
            answer = answer,
            modifier = Modifier.weight(1f).fillMaxWidth(1f)
        )
    }
}