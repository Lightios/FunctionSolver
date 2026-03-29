package pl.michal_cyran.function_solver.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em

//package pl.michal_cyran.function_solver.theme// Cyan color palette for Jetpack Compose
//import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.darkColorScheme
//import androidx.compose.material3.lightColorScheme
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.graphics.Color
//
//object CyanColors {
//    // Primary cyan colors
//    val Cyan50 = Color(0xFFECFDFF)   // Very light cyan for light theme backgrounds
//    val Cyan100 = Color(0xFFCFF9FE)  // Light cyan for light theme cards
//    val Cyan200 = Color(0xFF9FF2FD)  // Light cyan borders
//    val Cyan300 = Color(0xFF67E8F9)  // Medium cyan for dark theme text
//    val Cyan400 = Color(0xFF22D3EE)  // Primary cyan for dark theme
//    val Cyan500 = Color(0xFF06B6D4)  // Primary cyan for light theme
//    val Cyan600 = Color(0xFF0891B2)  // Darker cyan for hover states
//    val Cyan700 = Color(0xFF0E7490)  // Dark cyan
//    val Cyan800 = Color(0xFF155E75)  // Very dark cyan
//    val Cyan900 = Color(0xFF164E63)  // Darkest cyan
//}
//
//// Theme-specific color schemes
//object AppColors {
//    object Dark {
//        val primary = CyanColors.Cyan400
//        val primaryVariant = CyanColors.Cyan300
//        val secondary = CyanColors.Cyan300
//        val background = Color(0xFF14141F)    // gray-900
//        val surface = Color(0xFF171824)       // gray-800
//        val onPrimary = Color(0xFF111827)     // gray-900
//        val onSecondary = Color(0xFF111827)   // gray-900
//        val onBackground = Color.White
//        val onSurface = Color(0xFFD1D5DB)     // gray-300
//        val surfaceContainer = Color(0xFF181925) // gray-600
//        val accent = CyanColors.Cyan400
//    }
//}
//
//@Composable
//fun AppThemeM3(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colorScheme =
//        darkColorScheme(
//            primary = AppColors.Dark.primary,
//            secondary = AppColors.Dark.secondary,
//            background = AppColors.Dark.background,
//            surface = AppColors.Dark.surface,
//            onPrimary = AppColors.Dark.onPrimary,
//            onSecondary = AppColors.Dark.onSecondary,
//            onBackground = AppColors.Dark.onBackground,
//            onSurface = AppColors.Dark.onSurface,
//            primaryContainer = Color(24, 33, 47),
//            secondaryContainer = CyanColors.Cyan900.copy(alpha = 0.6f),
//            surfaceContainer = AppColors.Dark.surfaceContainer
//        )
//
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        content = content,
//        typography = AppTypography(),
//    )
//}


object AppColors {
    val Background        = Color(0xFF090D10)   // near-black
    val SurfaceL1         = Color(0xFF0D1A20)   // card base
    val SurfaceL2         = Color(0xFF111820)   // elevated card / tab bar
    val SurfaceL3         = Color(0xFF141E26)   // input bg
    val Border            = Color(0xFF12303A)   // subtle divider
    val Cyan             = Color(0xFF06B6D4)
    val CyanDim          = Color(0x2606B6D4)
    val CyanLight              = Color(0xFF67E8F9)   // graph / correct
    val CyanLightDim           = Color(0x1467E8F9)
    val Error             = Color(0xFFFF5F5F)
    val ErrorDim          = Color(0x33FF5F5F)
    val TextPrimary       = Color(0xFFE2F8FC)
    val TextSecondary     = Color(0xFF7AB8C4)
    val TextMuted         = Color(0xCCE2F8FC)
    val GraphLine         = Color(0xFF22D3EE)   // sky blue for function curve
    val GridLine          = Color(0xFF0A1E28)
    val GridAxis          = Color(0xFF3D6470)
}

private val MonoStyle = TextStyle(fontFamily = FontFamily.Monospace)
private val LabelStyle = TextStyle(
    fontFamily = FontFamily.Default,
    letterSpacing = 0.08.em,
    fontWeight = FontWeight.Medium
)