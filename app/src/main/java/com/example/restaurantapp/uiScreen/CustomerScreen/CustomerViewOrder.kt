package com.example.restaurantapp.uiScreen.CustomerScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar
import com.example.restaurantapp.uiScreen.components.RestaurantAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerViewOrder(
    modifier: Modifier,
    onBillClicked: () -> Unit,
    onPlaceOrderClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            RestaurantAppBar()
        },
        bottomBar = {
            CustomerBottomAppBar(
                modifier = Modifier,
                onBillClicked,
                onPlaceOrderClicked,
                "Place order",
                Icons.Filled.Check
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Vieworder")
                OrderList()
            }
        }
    }
}

@Composable
fun OrderList() {
    LazyColumn {
        items(5) { index ->
            OrderListItem(index)
        }
    }
}

@Composable
fun OrderListItem(index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text("$index")
        Column(modifier = Modifier.padding(horizontal = 5.dp)) {
            Text("Menu")
            Text("status")
        }
    }
    HorizontalDivider(thickness = 1.dp)


}

