package pl.michal_cyran.function_solver.ui.composables.reusable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import pl.michal_cyran.function_solver.function.domain.numbers_set.NumbersContainer
import pl.michal_cyran.function_solver.function.presentation.numbers_set.ListNumbersSetComposable
import pl.michal_cyran.function_solver.theme.AppColors

@Composable
fun Answer(
    propertyName: String,
    value: List<NumbersContainer>,
    onHover: () -> Unit,
    onUnhover: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val borderColor by animateColorAsState(
        if (isHovered) AppColors.CyanLight else AppColors.Border,
        tween(200)
    )
    val bgColor by animateColorAsState(
        if (isHovered) AppColors.CyanLightDim else Color.Transparent,
        tween(200)
    )

    LaunchedEffect(isHovered) {
        if (isHovered) onHover() else onUnhover()
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(AppColors.SurfaceL1)
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .hoverable(interactionSource)
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = propertyName.uppercase(),
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextMuted,
                        letterSpacing = 0.12.em,
                    )
                )
                if (isHovered) {
                    Text(
                        "na wykresie",
                        style = TextStyle(
                            fontSize = 9.sp,
                            color = AppColors.CyanLight,
                            fontFamily = FontFamily.Monospace,
                        )
                    )
                }
            }

            ListNumbersSetComposable(
                numbersContainers = value,
            )
        }
    }
}