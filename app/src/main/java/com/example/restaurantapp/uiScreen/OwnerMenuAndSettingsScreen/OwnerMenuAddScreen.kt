package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit  // or any icon if you prefer a different "History" icon
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerMenuAddScreen(
    restaurantName: String = "restaurant name",
    modifier: Modifier = Modifier,
    onMenuAdded: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    // Bottom bar callbacks:
    onIncomingClicked: () -> Unit = {},
    onManageClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {},
    onSettingClicked: () -> Unit = {}
) {
    // Local state variables for the menu fields
    var menuName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            // Top App Bar with a back arrow and the restaurant name
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text(text = restaurantName) }
            )
        },
        bottomBar = {
            // Bottom App Bar with 4 icons: Incoming, Manage, History, Setting
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Incoming
                    IconButton(onClick = onIncomingClicked) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Incoming"
                        )
                    }
                    // Manage
                    IconButton(onClick = onManageClicked) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Manage"
                        )
                    }
                    // History
                    IconButton(onClick = onHistoryClicked) {
                        Icon(
                            imageVector = Icons.Default.Edit,  // or any icon you want for "History"
                            contentDescription = "History"
                        )
                    }
                    // Setting
                    IconButton(onClick = onSettingClicked) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        // Main content: "Add new menu" heading, text fields, and an "Add menu" button
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            // Heading
            Text(text = "Add new menu", modifier = Modifier.padding(bottom = 8.dp))

            // Text fields for the menu
            TextField(
                value = menuName,
                onValueChange = { menuName = it },
                label = { Text("Menu name") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            TextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            TextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )
            TextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Image upload") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // The "Add Menu" button in purple
            Button(
                onClick = onMenuAdded,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B3C92)),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Add menu")
            }
        }
    }
}
