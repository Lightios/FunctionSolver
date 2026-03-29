package pl.michal_cyran.function_solver.ui.composables.reusable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import pl.michal_cyran.function_solver.theme.AppColors

@Composable
fun FunctionProperty(
    propertyName: String,
    value: String,
    onValueChange: (String) -> Unit,
    onCheckAnswer: () -> Unit,
    isAnswerCorrect: Boolean? = null,
    isThisChecked: Boolean = false,
    modifier: Modifier = Modifier
) {
    val stateColor = when {
        isThisChecked && isAnswerCorrect == true  -> AppColors.CyanLight
        isThisChecked && isAnswerCorrect == false -> AppColors.Error
        else -> AppColors.Border
    }
    val stateBg = when {
        isThisChecked && isAnswerCorrect == true  -> AppColors.CyanLightDim
        isThisChecked && isAnswerCorrect == false -> AppColors.ErrorDim
        else -> Color.Transparent
    }
    val supportingText = when {
        isThisChecked && isAnswerCorrect == true  -> "✓  Poprawna odpowiedź"
        isThisChecked && isAnswerCorrect == false -> "✗  Błędna odpowiedź"
        else -> ""
    }
    val textColor = when {
        isThisChecked && isAnswerCorrect == true  -> AppColors.CyanLight
        isThisChecked && isAnswerCorrect == false -> AppColors.Error
        else -> AppColors.TextPrimary
    }

    val borderColor by animateColorAsState(stateColor, tween(300))
    val bgColor by animateColorAsState(stateBg, tween(300))

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(AppColors.SurfaceL1)
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .padding(14.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // Label row
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = propertyName,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppColors.TextMuted,
                        letterSpacing = 0.12.em,
                    ),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                if (supportingText.isNotEmpty()) {
                    Text(
                        text = supportingText,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = borderColor,
                            letterSpacing = 0.06.em,
                        )
                    )
                }
            }

            // Input + button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(6.dp))
                        .background(AppColors.SurfaceL3)
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp,
                        color = textColor,
                    ),
                    cursorBrush = SolidColor(AppColors.Cyan),
                    singleLine = true,
                    decorationBox = { inner ->
                        Box {
                            if (value.isEmpty()) {
                                Text(
                                    "np.  [−2, 4]",
                                    style = TextStyle(
                                        fontFamily = FontFamily.Monospace,
                                        fontSize = 14.sp,
                                        color = AppColors.TextMuted,
                                    )
                                )
                            }
                            inner()
                        }
                    }
                )

                // Check button
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(AppColors.CyanDim)
                        .border(1.dp, AppColors.Cyan.copy(0.5f), RoundedCornerShape(8.dp))
                        .clickable { onCheckAnswer() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sprawdź",
                        fontSize = 14.sp,
                        color = AppColors.Cyan,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}