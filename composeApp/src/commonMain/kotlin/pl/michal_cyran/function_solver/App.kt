package pl.michal_cyran.function_solver

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import pl.michal_cyran.function_solver.ui.composables.MainScreen
import pl.michal_cyran.function_solver.ui.view_model.FunctionViewModel
import pl.michal_cyran.website.ui.theme.AppThemeM3

@Composable
@Preview
fun App(
    viewModel: FunctionViewModel
) {
    AppThemeM3(darkTheme = true) {
        MainScreen(
            viewModel = viewModel
        )
    }
}