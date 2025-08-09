package pl.michal_cyran.function_solver

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "FunctionSolver",
//        alwaysOnTop = true,
    ) {
        App()
    }
}