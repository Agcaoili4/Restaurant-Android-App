package com.example.restaurantapp.uiScreen.CustomerScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.ViewModel
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar
import com.example.restaurantapp.uiScreen.components.RestaurantAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerViewOrder(
    modifier: Modifier,
    onBillClicked: () -> Unit,
    onViewOrderClicked: () -> Unit,
    onCallwaiterClicked: () -> Unit,
    onMenuClicked: () -> Unit,
    onPlaceorderClicked:()->Unit,
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
                "Place order",
                Icons.Filled.Check,
                viewModel=viewModel
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
                OrderList()

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(modifier=Modifier.fillMaxWidth()) {
                        Button(onClick = onPlaceorderClicked,modifier=Modifier.fillMaxWidth())
                        { Text("Place order") }
                    }
                }
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

