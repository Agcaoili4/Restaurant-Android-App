package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OwnerBottomAppBar(
    onIncomingClicked: () -> Unit,
    onManageClicked: () -> Unit,
    onHistoryClicked: () -> Unit,
    onSettingClicked: () -> Unit
) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Incoming Orders
            IconButton(onClick = onIncomingClicked) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Incoming Orders"
                )
            }

            // Manage Menu
            IconButton(onClick = onManageClicked) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Manage Menu"
                )
            }

            // History (using AccountCircle as alternative)
            IconButton(onClick = onHistoryClicked) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "History"
                )
            }

            // Settings
            IconButton(onClick = onSettingClicked) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings"
                )
            }
        }
    }
}
