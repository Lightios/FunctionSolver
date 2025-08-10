package pl.michal_cyran.function_solver

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import pl.michal_cyran.function_solver.ui.view_model.FunctionViewModel

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val viewModel = FunctionViewModel()
        App(viewModel)
    }
}