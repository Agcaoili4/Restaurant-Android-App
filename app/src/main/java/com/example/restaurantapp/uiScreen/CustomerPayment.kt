package com.example.restaurantapp.uiScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomerPayment(modifier: Modifier,
                    onPayClicked:()->Unit){

    Column { Text("Payment")
    Button(onClick = onPayClicked)
     { Text("Pay now") }}
}