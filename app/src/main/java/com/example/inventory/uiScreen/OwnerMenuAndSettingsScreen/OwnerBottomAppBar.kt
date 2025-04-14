package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun OwnerBottomAppBar(
    selectedIndex: Int,
    onIncomingClicked: () -> Unit,
    onManageClicked: () -> Unit,
    onHistoryClicked: () -> Unit,
    onSettingClicked: () -> Unit
) {
    val items = listOf("Incoming", "Manage", "History", "Setting")
    val icons = listOf(
        Icons.Default.Notifications,
        Icons.Default.List,
        Icons.Default.Edit,
        Icons.Default.Settings
    )

    NavigationBar(containerColor = Color.White) {
        items.forEachIndexed { index, label ->
            NavigationBarItem(
                icon = { Icon(imageVector = icons[index], contentDescription = label) },
                label = { Text(label) },
                selected = selectedIndex == index,
                onClick = {
                    when (index) {
                        0 -> onIncomingClicked()
                        1 -> onManageClicked()
                        2 -> onHistoryClicked()
                        3 -> onSettingClicked()
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF7B3C92),
                    selectedTextColor = Color(0xFF7B3C92),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
