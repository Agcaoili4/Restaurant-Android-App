package com.example.restaurantapp.uiScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerViewOrder(
    modifier: Modifier,
    onBillClicked: () -> Unit,
    onPlaceOrderClicked: () -> Unit
) {
    Scaffold(
        bottomBar = {
            CustomerBottomAppBar(
                modifier = Modifier,
                onBillClicked,
                onPlaceOrderClicked,
                "Place order",
                Icons.Filled.Check
            )
        }
    ) {
        Column { Text("Vieworder") }
    }

}

