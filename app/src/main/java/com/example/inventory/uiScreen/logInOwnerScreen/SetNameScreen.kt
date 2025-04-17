package com.example.restaurantapp.uiScreen.logInOwnerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SetRestaurantNameScreen(onNameSet: () -> Unit) {
    val context = LocalContext.current
    val preference = remember { UserPreferences(context) }
    var restaurantName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Restaurant App",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(20.dp)
        )
        TextField(
            value = restaurantName,
            onValueChange = { restaurantName = it },
            label = { Text("Enter Restaurant Name") }
        )

        Button(onClick = {
            // Stores user to the data to be a current user and not a new one
            if (restaurantName.isNotEmpty()) {
                preference.setNewUser(false)
                onNameSet()

            }
        },
            modifier = Modifier.padding(20.dp)) {
            Text("Continue")
        }
    }
}


@Preview
@Composable
fun PreviewRestaurantNameScreen(){
    SetRestaurantNameScreen {  }
}