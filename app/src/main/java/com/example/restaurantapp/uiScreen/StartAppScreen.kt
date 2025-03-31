package com.example.restaurantapp.uiScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar
import com.example.restaurantapp.uiScreen.components.RestaurantAppBar

@Composable
fun StartAppScreen(
    modifier: Modifier,
    onTempYositaButton: () -> Unit
) {

        Column(        modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text("Login page")
            Button(onClick = onTempYositaButton) {
                Text("Go to Yosita part")
            }
        }
    }
