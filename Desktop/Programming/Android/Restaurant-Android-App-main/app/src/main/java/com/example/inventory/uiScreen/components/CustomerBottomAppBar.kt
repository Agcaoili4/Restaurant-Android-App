package com.example.restaurantapp.uiScreen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel

@Composable
fun CustomerBottomAppBar(
    modifier: Modifier,
    onBillClicked: () -> Unit,
    onViewOrderClicked: () -> Unit,
    onCallwaiterClicked: () -> Unit,
    onMenuClicked: () -> Unit,
    LargeButtonText: String,
    LargeButtonIcon: ImageVector,
    viewModel: CustomerViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    val bottomBar = uiState.bottomBar

    val items = listOf("Call waiter", "Menu", "View order", "Bill")
    val icons = listOf(
        Icons.Default.Notifications,
        Icons.Default.List,
        Icons.Default.ShoppingCart,
        Icons.Default.Check
    )
    val onClicks = listOf(onCallwaiterClicked, onMenuClicked, onViewOrderClicked, onBillClicked)

    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        items.forEachIndexed { index, label ->
            NavigationBarItem(
                icon = { Icon(imageVector = icons[index], contentDescription = label) },
                label = { Text(label) },
                selected = bottomBar == index,
                onClick = {
                    viewModel.setBottomBar(index)
                    onClicks[index]()
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
