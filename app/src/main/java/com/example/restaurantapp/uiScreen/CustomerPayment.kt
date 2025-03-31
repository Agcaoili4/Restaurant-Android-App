package com.example.restaurantapp.uiScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar
import com.example.restaurantapp.uiScreen.components.RestaurantAppBar

@Composable
fun CustomerPayment(
    modifier: Modifier,
    onPayClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            RestaurantAppBar()
        }
    ) { innerPadding ->
        Column(modifier=Modifier.padding(innerPadding)) {
            Column(modifier=Modifier.padding(8.dp)) {
                Text("Payment")
                Button(onClick = onPayClicked)
                { Text("Pay now") }
            }

        }
    }
}