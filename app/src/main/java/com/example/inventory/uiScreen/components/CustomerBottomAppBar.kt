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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    var bottomBar = uiState.bottomBar


    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Call waiter","Menu", "View order", "Bill")
    val selectedIcons =
        listOf(Icons.Filled.Notifications, Icons.Filled.List, Icons.Filled.ShoppingCart, Icons.Filled.Check)
    val unselectedIcons =
        listOf(Icons.Outlined.Notifications, Icons.Filled.List, Icons.Outlined.ShoppingCart, Icons.Outlined.Check)
    val onclick = listOf(onCallwaiterClicked,onMenuClicked, onViewOrderClicked, onBillClicked)

    BottomAppBar(
        actions = {



            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (bottomBar == index) selectedIcons[index] else unselectedIcons[index],
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = bottomBar == index,
                        onClick = {
                            viewModel.setBottomBar(index)
                            onclick[index]()
                        }
                    )
                }
            }

        }
    )
}

