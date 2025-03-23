package com.example.restaurantapp.uiScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StartAppScreen(
    modifier: Modifier,
    onTempYositaButton: () -> Unit
) {
    Column {
        Text("Login page")
        Button(onClick = onTempYositaButton) {
            Text("Go to Yosita part")
        }
    }

}