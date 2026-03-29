package pl.michal_cyran.function_solver.ui.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import pl.michal_cyran.function_solver.theme.AppColors

@Composable
fun Header(
    onGenerateContinuousFunction: () -> Unit,
    onGeneratePiecewiseFunction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(AppColors.SurfaceL1)
            .drawBehind {
                // amber bottom border line
                drawLine(
                    color = AppColors.Cyan,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2f
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Amber bracket decoration
            Text(
                text = "f(x)",
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.Cyan,
                    letterSpacing = 0.05.em,
                ),
                modifier = Modifier
                    .border(1.dp, AppColors.CyanDim, RoundedCornerShape(4.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )

            Text(
                text = "Odczytywanie własności funkcji z wykresu",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColors.TextPrimary,
                    letterSpacing = 0.02.em,
                ),
                modifier = Modifier.weight(1f)
            )

            LabButton(
                text = "Ciągła",
                onClick = onGenerateContinuousFunction,
            )
            LabButton(
                text = "Przedziałowa",
                onClick = onGeneratePiecewiseFunction,
            )
        }
    }
}


@Composable
fun LabButton(
    text: String,
    onClick: () -> Unit,
) {
    var hovered by remember { mutableStateOf(false) }
    val bgAlpha by animateFloatAsState(if (hovered) 1f else 0f, tween(150))
    val borderAlpha by animateFloatAsState(if (hovered) 1f else 0.4f, tween(150))

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(AppColors.Cyan.copy(alpha = bgAlpha * 0.15f))
            .border(
                1.dp,
                AppColors.Cyan.copy(alpha = borderAlpha),
                RoundedCornerShape(6.dp)
            )
            .clickable { onClick() }
            .hoverable(remember { MutableInteractionSource() }.also {
                // simplified — wire hover in real impl
            })
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.TextPrimary,
                    letterSpacing = 0.04.em,
                )
            )
        }
    }
}
