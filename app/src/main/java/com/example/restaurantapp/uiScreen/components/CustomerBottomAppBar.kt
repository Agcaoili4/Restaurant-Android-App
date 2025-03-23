package com.example.restaurantapp.uiScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomerBottomAppBar(
    modifier: Modifier,
    onBillClicked: () -> Unit,
    onLargeButtonClicked: () -> Unit,
    LargeButtonText: String,
    LargeButtonIcon: ImageVector
) {
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Localized description",
                    )
                }
                Button(
                    onClick = onBillClicked,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 0.dp)
                ) {
                    Icon(Icons.Filled.Star, null)
                    Text("  Bill")
                }

                Button(
                    onClick = onLargeButtonClicked,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 0.dp)
                        .fillMaxWidth()
                ) {
                    Icon(LargeButtonIcon, null)
                    Text("  " + LargeButtonText)
                }

            }
        }
    )
}

