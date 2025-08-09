package pl.michal_cyran.website.ui.theme// Cyan color palette for Jetpack Compose
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object CyanColors {
    // Primary cyan colors
    val Cyan50 = Color(0xFFECFDFF)   // Very light cyan for light theme backgrounds
    val Cyan100 = Color(0xFFCFF9FE)  // Light cyan for light theme cards
    val Cyan200 = Color(0xFF9FF2FD)  // Light cyan borders
    val Cyan300 = Color(0xFF67E8F9)  // Medium cyan for dark theme text
    val Cyan400 = Color(0xFF22D3EE)  // Primary cyan for dark theme
    val Cyan500 = Color(0xFF06B6D4)  // Primary cyan for light theme
    val Cyan600 = Color(0xFF0891B2)  // Darker cyan for hover states
    val Cyan700 = Color(0xFF0E7490)  // Dark cyan
    val Cyan800 = Color(0xFF155E75)  // Very dark cyan
    val Cyan900 = Color(0xFF164E63)  // Darkest cyan
}

// Theme-specific color schemes
object AppColors {
    // Light theme colors
    object Light {
        val primary = CyanColors.Cyan500
        val primaryVariant = CyanColors.Cyan600
        val secondary = CyanColors.Cyan400
        val background = Color(0xFFf2f3f4)
        val surface = Color(0xFFf0f0f0)  // gray-50
        val onPrimary = Color.White
        val onSecondary = Color.White
        val onBackground = Color(0xFF111827)  // gray-900
        val onSurface = Color(0xFF374151)     // gray-600
        val accent = CyanColors.Cyan500
    }
    
    // Dark theme colors
    object Dark {
        val primary = CyanColors.Cyan400
        val primaryVariant = CyanColors.Cyan300
        val secondary = CyanColors.Cyan300
        val background = Color(0xFF14141F)    // gray-900
        val surface = Color(0xFF171824)       // gray-800
        val onPrimary = Color(0xFF111827)     // gray-900
        val onSecondary = Color(0xFF111827)   // gray-900
        val onBackground = Color.White
        val onSurface = Color(0xFFD1D5DB)     // gray-300
        val surfaceContainer = Color(0xFF181925) // gray-600
        val accent = CyanColors.Cyan400
    }
}

@Composable
fun AppThemeM3(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = AppColors.Dark.primary,
            secondary = AppColors.Dark.secondary,
            background = AppColors.Dark.background,
            surface = AppColors.Dark.surface,
            onPrimary = AppColors.Dark.onPrimary,
            onSecondary = AppColors.Dark.onSecondary,
            onBackground = AppColors.Dark.onBackground,
            onSurface = AppColors.Dark.onSurface,
            primaryContainer = Color(24, 33, 47),
            secondaryContainer = CyanColors.Cyan900.copy(alpha = 0.6f),
            surfaceContainer = AppColors.Dark.surfaceContainer
        )
    } else {
        lightColorScheme(
            primary = AppColors.Light.primary,
//            secondary = AppColors.Light.secondary,
//            background = AppColors.Light.background,
//            surface = AppColors.Light.surface,
            onPrimary = AppColors.Light.onPrimary,
//            onSecondary = AppColors.Light.onSecondary,
//            onBackground = AppColors.Light.onBackground,
//            onSurface = AppColors.Light.onSurface,
            primaryContainer = Color(233, 253, 255),
//            secondaryContainer = Color(187, 238, 245)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = AppTypography(),
    )
}