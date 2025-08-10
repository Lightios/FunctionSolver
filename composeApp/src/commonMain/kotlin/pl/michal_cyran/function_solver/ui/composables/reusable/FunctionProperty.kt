package pl.michal_cyran.function_solver.ui.composables.reusable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.CheckResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    var color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    var supportingText = ""

    if (isThisChecked) {
        if (isAnswerCorrect != null) {
            if (isAnswerCorrect) {
                color = MaterialTheme.colorScheme.primary
                supportingText = "Poprawna odpowiedź"
            } else {
                color = MaterialTheme.colorScheme.error
                supportingText = "Błędna odpowiedź"
            }
        }
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(6.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = propertyName,
                    style = MaterialTheme.typography.labelMedium,
                )

                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(1f).height(90.dp),
                    singleLine = true,
                    shape = RectangleShape,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                        cursorColor = MaterialTheme.colorScheme.primary
                    ),
                    supportingText = {
                        Text(
                            text = supportingText,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        color = color,
                    )
                )
            }

            Button(
                onClick = onCheckAnswer,
                modifier = Modifier
                    .padding(16.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    "Sprawdź"
                )
            }
        }
    }
}
