package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.inventory.DatabaseViewModel
import com.example.inventory.R
import com.example.inventory.data.Customer
import com.example.inventory.data.Menu
import com.example.inventory.data.Notification
import com.example.inventory.data.Order
import com.example.inventory.data.OrderDetail
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel
import com.example.restaurantapp.uiScreen.CustomerScreen.MenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerIncomingScreen(
    modifier: Modifier = Modifier,
    onMenuAdded: () -> Unit = {},
    onIncomingClicked: () -> Unit = {},
    onManageClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {},
    onSettingClicked: () -> Unit = {},
    databaseViewModel: DatabaseViewModel,
) {

    val notifications by databaseViewModel.notifications.collectAsState(emptyList())
    val orders by databaseViewModel.orders.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Incoming") }
            )
        },
        bottomBar = {
            OwnerBottomAppBar(
                selectedIndex = 0,
                onIncomingClicked = onIncomingClicked,
                onManageClicked = onManageClicked,
                onHistoryClicked = onHistoryClicked,
                onSettingClicked = onSettingClicked
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {


            Text(
                text = "Notifications",
                modifier = Modifier
                    .padding(10.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                items(notifications) { noti ->
                    NotiItem(noti, databaseViewModel)
                }
            }

            Text(
                text = "Orders",
                modifier = Modifier
                    .padding(10.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                items(orders) { order ->
                    OrderItem(order, databaseViewModel)
                }
            }
        }
    }
}


@Composable
fun NotiItem(noti: Notification, databaseViewModel: DatabaseViewModel) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {

            var customer by remember { mutableStateOf<Customer?>(null) }
            LaunchedEffect(noti.customerId) {
                customer = databaseViewModel.getCustomerById(noti.customerId)
            }

            Text(
                text = "Table ${customer?.tableNumber}",
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(noti.title)
            }
        }
    }
}

@Composable
fun OrderItem(order: Order, databaseViewModel: DatabaseViewModel) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {

            var customer by remember { mutableStateOf<Customer?>(null) }
            LaunchedEffect(order.customerId) {
                customer = databaseViewModel.getCustomerById(order.customerId)
            }

            Text(
                text = "Table ${customer?.tableNumber}",
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    val orders by databaseViewModel.orderdetalis(order.orderId).collectAsState(initial = emptyList())



                    orders.forEach { order ->
                        var menu by remember { mutableStateOf<Menu?>(null) }

                        LaunchedEffect(order.menuId) {
                            menu = databaseViewModel.getMenuById(order.menuId)
                        }
                        Text(text = "${order.quantity}x ${menu?.name}")
                    }
                }


//                Text(noti.title)
//                Text(text = noti.status)
            }
        }
    }
}


