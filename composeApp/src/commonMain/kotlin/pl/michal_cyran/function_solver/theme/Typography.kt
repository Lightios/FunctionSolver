package pl.michal_cyran.website.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTypography {
    val headlineLarge = TextStyle(
        fontSize = 65.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 60.sp,
    )

    val titleLarge = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 36.sp,
    )

    val titleMedium = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp,
    )

    val bodyLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 25.sp,
    )

    val bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
    )

    val labelMedium = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
    )

    val labelSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
    )
}

// Usage in Compose
@Composable
fun AppTypography() = Typography(
//    displayLarge = AppTypography2.heading1XLarge,
//    displayMedium = AppTypography2.heading1Large,
//    displaySmall = AppTypography.heading1,
    headlineLarge = AppTypography.headlineLarge,
//    headlineMedium = AppTypography2.heading2,
//    headlineSmall = AppTypography2.heading3,
    titleLarge = AppTypography.titleLarge,
    titleMedium = AppTypography.titleMedium,
    bodyLarge = AppTypography.bodyLarge,
    bodyMedium = AppTypography.bodyMedium,
//    bodySmall = AppTypography2.bodySmall,
//    labelLarge = AppTypography2.buttonLarge,
    labelMedium = AppTypography.labelMedium,
    labelSmall = AppTypography.labelSmall
)