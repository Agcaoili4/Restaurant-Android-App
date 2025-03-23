package com.example.restaurantapp.uiScreen

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.text.Layout.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.components.CustomerBottomAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomerMenuList(
    modifier: Modifier,
    onViewOrderClicked: () -> Unit,
    onBillClicked: () -> Unit
) {
    Scaffold(
        bottomBar = {
            CustomerBottomAppBar(
                modifier = Modifier,
                onBillClicked,
                onViewOrderClicked,
                "View Order",
                Icons.Filled.ShoppingCart
            )
        }
    ) {
        Column(modifier = Modifier) {
            Text("MenuList")
            Text(
                text = "Example of a scaffold with a bottom app bar."
            )
        }
    }
}








