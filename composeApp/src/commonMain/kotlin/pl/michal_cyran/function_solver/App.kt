package pl.michal_cyran.function_solver

import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import pl.michal_cyran.function_solver.ui.composables.MainScreen
import pl.michal_cyran.website.ui.theme.AppThemeM3

@Composable
@Preview
fun App() {
    AppThemeM3(darkTheme = true) {
        MainScreen()
//        var function by remember { mutableStateOf(generateFunction()) }
//        var answer by remember { mutableStateOf(emptyList<NumbersInterval>()) }
//
//        Column(
//            modifier = Modifier
//                .background(MaterialTheme.colorScheme.primaryContainer)
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
////            val function = Function(
////                listOf(
////                    Interval(
////                        listOf(
////                            Point(0f, 0f),
////                            Point(1f, 1f),
////                            Point(2f, 1f),
////                            Point(3f, 3f),
////                            Point(4f, 0f)
////                        )
////                    )
////                )
////            )
//
//
//            FunctionGraph(
//                function = function,
//                modifier = Modifier.weight(1f).fillMaxWidth()
//            )
//
//            if (answer.isNotEmpty()) {
//                Row(
//                    modifier = Modifier
//                        .background(MaterialTheme.colorScheme.secondaryContainer),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    Text(
//                        text = "Dziedzina funkcji:",
//                        style = MaterialTheme.typography.titleLarge,
//                    )
//                    answer.forEach { numbersSet ->
//                        NumbersSetComposable(numbersSet = numbersSet)
//                    }
//                }
//            }
//
//            LazyRow(
//                horizontalArrangement = Arrangement.spacedBy(10.dp)
//            ) {
//                item {
//
//                    Button(
//                        onClick = { function = generateFunction() },
//                    ) {
//                        Text(text = "Wygneruj nową funkcję")
//                    }
//                }
//
//                item {
//                    Button(
//                        onClick = { answer = Domain(function).get() }
//                    ) {
//                        Text(text = "Wyznacz dziedzinę")
//                    }
//                }
//
//                item {
//                    Button(
//                        onClick = { answer = ValueRange(function).get() }
//                    ) {
//                        Text(text = "Wyznacz zbiór wartości")
//                    }
//                }
//
//                item {
//                    Button(
//                        onClick = { answer = Monotonicity(function).getAscending() }
//                    ) {
//                        Text(text = "Wyznacz monotoniczność rosnącą")
//                    }
//                }
//
//                item {
//                    Button(
//                        onClick = { answer = Monotonicity(function).getDescending() }
//                    ) {
//                        Text(text = "Wyznacz monotoniczność malejącą")
//                    }
//                }
//
//                item {
//                    Button(
//                        onClick = { answer = Monotonicity(function).getConstant() }
//                    ) {
//                        Text(text = "Wyznacz monotoniczność stałą")
//                    }
//                }
//            }
//        }
    }
}