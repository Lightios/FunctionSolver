package pl.michal_cyran.function_solver.ui.composables.reusable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf("") }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceContainer,
            contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = propertyName,
                style = MaterialTheme.typography.labelMedium,
            )

            TextField(
                value = value,
                onValueChange = { value = it },
                modifier = Modifier.fillMaxWidth(1f).height(50.dp),
                singleLine = true,
                shape = RectangleShape,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
                textStyle = TextStyle(
                    fontSize = 12.sp,
                )
            )
        }
    }
}
