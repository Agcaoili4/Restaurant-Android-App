package com.example.restaurantapp.uiScreen.CustomerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar
import com.example.restaurantapp.uiScreen.components.RestaurantAppBar

@Composable
fun CustomerPayment(
    modifier: Modifier,
    onPayClicked: () -> Unit,
    onBillClicked: () -> Unit,
    onViewOrderClicked: () -> Unit,
    onCallwaiterClicked: () -> Unit,
    onMenuClicked: () -> Unit,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
) {
    val uiState by customerViewModel.uiState.collectAsState()
    var tableNumber = uiState.currentTableNumber
    var totalPrice = uiState.currentTotalPrice
    var currentOrderId = uiState.currentOrderId

    val orders by databaseViewModel.orderdetalis(currentOrderId).collectAsState(emptyList())



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
                "View Order",
                Icons.Filled.ShoppingCart,
                viewModel = customerViewModel
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Payment Receipt",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(5.dp)
                )

                Text(text="Table $tableNumber",modifier = Modifier.padding(5.dp))
                HorizontalDivider(thickness = 1.dp)
                BillList(orders,
                    customerViewModel,
                    databaseViewModel
                    )
                Row(
                    modifier = Modifier.padding(5.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total Amount")
                    Text(String.format("%.2f", totalPrice))
                }
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(modifier=Modifier.fillMaxWidth()) {
                        Button(onClick = onPayClicked,modifier=Modifier.fillMaxWidth())
                        { Text("Pay now") }
                    }
                }


            }

        }
    }
}

@Composable
fun BillList(orders:List<OrderDetail>,   customerViewModel: CustomerViewModel,databaseViewModel: DatabaseViewModel) {

    LazyColumn {
        items(orders) { o ->
            BillListItem(o.menuId,o.quantity, customerViewModel ,databaseViewModel)
        }
    }
    HorizontalDivider(thickness = 1.dp)


    var totalPrice by remember { mutableStateOf(0.0) }

    // Reset total before calculating
    LaunchedEffect(orders) {
        var total = 0.0
        orders.forEach { o ->
            val menu = databaseViewModel.getMenuById(o.menuId)
            total += (menu?.price ?: 0.0) * o.quantity
        }
        totalPrice = total
        customerViewModel.setTotalPrice(total)
    }
}

@Composable
fun BillListItem(menuId: Int,
                 quantity:Int,
                 customerViewModel: CustomerViewModel,
                 databaseViewModel: DatabaseViewModel) {
    // Variable to store the menu data
    var menu by remember { mutableStateOf<Menu?>(null) }

    // Collect the data once when menuId changes
    LaunchedEffect(menuId) {
        // Call suspend function to get the menu once
        menu = databaseViewModel.getMenuById(menuId)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Text("$quantity")
            Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                Text("${menu?.name}")
            }
        }
        Text("${menu?.price}")

    }
}

