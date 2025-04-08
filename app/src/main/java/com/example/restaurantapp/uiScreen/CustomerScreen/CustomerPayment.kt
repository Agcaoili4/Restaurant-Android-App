package com.example.restaurantapp.uiScreen.CustomerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.ViewModel
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
    viewModel: ViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var tableNumber = uiState.tableNumber

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
                viewModel = viewModel
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

                Text("Table $tableNumber")
                Text("Date: 2025-01-01")
                HorizontalDivider(thickness = 1.dp)
                BillList()
                Row(
                    modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total Amount")
                    Text("$333")
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
fun BillList() {
    LazyColumn {
        items(5) { index ->
            BillListItem(index)
        }
    }
    HorizontalDivider(thickness = 1.dp)
}

@Composable
fun BillListItem(index: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Text("$index")
            Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                Text("Menu")
            }
        }



        Text("77")
    }


}