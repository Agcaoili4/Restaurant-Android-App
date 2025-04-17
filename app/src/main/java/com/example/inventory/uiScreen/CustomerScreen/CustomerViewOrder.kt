package com.example.restaurantapp.uiScreen.CustomerScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.inventory.DatabaseViewModel
import com.example.inventory.data.Menu
import com.example.inventory.data.OrderDetail
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel
import com.example.inventory.uiScreen.CustomerScreen.Order
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar
import com.example.restaurantapp.uiScreen.components.RestaurantAppBar
import kotlinx.coroutines.flow.Flow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerViewOrder(
    modifier: Modifier,
    onBillClicked: () -> Unit,
    onViewOrderClicked: () -> Unit,
    onCallwaiterClicked: () -> Unit,
    onMenuClicked: () -> Unit,
    onPlaceorderClicked: () -> Unit,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
) {
    val uiState by customerViewModel.uiState.collectAsState()
    var tableNumber = uiState.currentTableNumber
    var currentOrder = uiState.currentOrderList
    var currentOrderId = uiState.currentOrderId

    val orders by databaseViewModel.orderdetalis(currentOrderId).collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            RestaurantAppBar(tableNumber)
        },
        bottomBar = {
            CustomerBottomAppBar(
                modifier = Modifier,
                onBillClicked = onBillClicked,
                onViewOrderClicked = onViewOrderClicked,
                onCallwaiterClicked = onCallwaiterClicked,
                onMenuClicked = onMenuClicked,
                "Place order",
                Icons.Filled.Check,
                viewModel = customerViewModel
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = "View Order",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(5.dp)
                )

                AllOrderList(
                    orders,
                    customerViewModel = customerViewModel,
                    databaseViewModel = databaseViewModel
                )

                CurrentOrderList(
                    currentOrder,
                    customerViewModel = customerViewModel,
                    databaseViewModel = databaseViewModel
                )

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = {
                                currentOrder.forEach { o -> databaseViewModel.insertOrderDetail(o) }
                                customerViewModel.resetOrderList()
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        { Text("Place order") }
                    }
                }
            }
        }
    }
}

@Composable
fun AllOrderList(
    order: List<OrderDetail>,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
) {
    LazyColumn {
        items(order) { index ->
            AllOrderListItem(
                index,
                customerViewModel = customerViewModel,
                databaseViewModel = databaseViewModel
            )
        }
    }
}

@Composable
fun AllOrderListItem(
    index: OrderDetail,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp).background(
                color = MaterialTheme.colorScheme.primaryContainer
            )
    ) {
        Text("${index.quantity}x")
        Column(modifier = Modifier.padding(horizontal = 5.dp)) {
            MenuName(index.menuId, databaseViewModel)
            Text("Waiting...")
        }
    }
    HorizontalDivider(thickness = 1.dp)
}

@Composable
fun CurrentOrderList(
    order: List<OrderDetail>,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
) {
    LazyColumn {
        items(order) { index ->
            CurrentOrderListItem(
                index,
                customerViewModel = customerViewModel,
                databaseViewModel = databaseViewModel
            )
        }
    }
}

@Composable
fun CurrentOrderListItem(
    index: OrderDetail,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        // Quantity and Menu Name
        Text("${index.quantity}x")
        Column(modifier = Modifier.padding(horizontal = 5.dp)) {
            var menu by remember { mutableStateOf<Menu?>(null) }
            LaunchedEffect(index.menuId) { menu = databaseViewModel.getMenuById(index.menuId) }
            Text(text = menu?.name ?: "Loading...")
        }
        Spacer(modifier = Modifier.weight(1f))
        // Remove button
        IconButton(onClick = { customerViewModel.removeOrderListItem(index) }) {
            Icon(Icons.Default.Delete, contentDescription = "Remove item")
        }
    }
    HorizontalDivider(thickness = 1.dp)
}

@Composable
fun MenuName(menuId: Int, viewModel: DatabaseViewModel) {

    var menu by remember { mutableStateOf<Menu?>(null) }

    LaunchedEffect(menuId) {
        menu = viewModel.getMenuById(menuId)
    }

    // Display the menu name, or show a loading text
    Text(text = menu?.name ?: "Loading...")
}

