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
import androidx.compose.material.icons.filled.Edit
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
fun OwnerMenuUpdateScreen(
    restaurantName: String = "restaurant name",
    modifier: Modifier = Modifier,
    onDeleteClicked: () -> Unit = {},
    onUpdateClicked: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    // Optionally, add bottom bar callbacks:
    onIncomingClicked: () -> Unit = {},
    onManageClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {},
    onSettingClicked: () -> Unit = {}
) {
    // Local state variables for menu data
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    Scaffold(
        // Top bar with the restaurant name
        topBar = {
            TopAppBar(
                title = { Text(text = restaurantName) },
                navigationIcon = {
                    // Optional: If you want a back icon, you can do something like:
                    // IconButton(onClick = onNavigateBack) {
                    //     Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    // }
                }
            )
        },
        // Bottom bar with the 4 icons: Incoming, Manage, History, Setting
        bottomBar = {
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
                    // History (If you want a specific icon for history, replace 'Edit')
                    IconButton(onClick = onHistoryClicked) {
                        Icon(
                            imageVector = Icons.Default.Edit, // or any icon you want for "History"
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            // Title: "Edit menu"
            Text(
                text = "Edit menu",
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // TextFields for Menu Name, Description, Category, Price, Image Upload
            TextField(
                value = name,
                onValueChange = { name = it },
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

            // Row with Delete (red) and Update (purple) buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Delete button
                Button(
                    onClick = onDeleteClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Delete")
                }
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                // Update button
                Button(
                    onClick = onUpdateClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B3C92)), // Purple
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Update")
                }
            }
        }
    }
}
