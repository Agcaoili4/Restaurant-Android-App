package com.example.restaurantapp.uiScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.ViewModel


@Composable
fun CustomerBottomAppBar(
    modifier: Modifier,
    onBillClicked: () -> Unit,
    onViewOrderClicked: () -> Unit,
    onCallwaiterClicked: () -> Unit,
    onMenuClicked: () -> Unit,
    LargeButtonText: String,
    LargeButtonIcon: ImageVector,
    viewModel: ViewModel,
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
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                IconButton(onClick = { /* do something */ }) {
//                    Icon(
//                        Icons.Filled.Notifications,
//                        contentDescription = "Localized description",
//                    )
//                }
//
//                Button(
//                    onClick = onBillClicked,
//                    modifier = Modifier
//                        .padding(horizontal = 8.dp, vertical = 0.dp)
//                ) {
//                    Icon(Icons.Filled.Star, null)
//                    Text(" Bill")
//                }
//
//                Button(
//                    onClick = onLargeButtonClicked,
//                    modifier = Modifier
//                        .padding(horizontal = 8.dp, vertical = 0.dp)
//                        .fillMaxWidth()
//                ) {
//                    Icon(LargeButtonIcon, null)
//                    Text("  " + LargeButtonText)
//                }
//
//            }


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

