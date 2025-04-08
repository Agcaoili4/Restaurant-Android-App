package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.AccountCircle  // Use AccountCircle instead of ManageAccounts
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.logInOwnerScreen.UserPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerSettingScreen(
    modifier: Modifier = Modifier,
    userPreferences: UserPreferences,
    onLogoutClicked: () -> Unit,
    onNavigateBack: () -> Unit = {}
) {
    var email by remember { mutableStateOf("app@mybvc.ca") }
    var restaurantName by remember { mutableStateOf("XXX") }
    var phoneNumber by remember { mutableStateOf("XXX-XXX-XXXX") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .navigationBarsPadding()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { /* Navigate to Incoming if desired */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Incoming",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    IconButton(onClick = { /* Navigate to Manage if desired - using AccountCircle here */ }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Manage",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                    IconButton(onClick = { /* Currently on Settings */ }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(36.dp)
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
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Account heading
            Text("Account", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            // Display email
            Text("Email: $email")
            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            // Setting heading
            Text("Setting", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            // Container with a background (light pink)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RectangleShape)
                    .background(Color(0xFFFFF0F7))
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Restaurant name")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(restaurantName)
                            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                            IconButton(onClick = { /* Handle editing restaurant name */ }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit restaurant name"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Phone number")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(phoneNumber)
                            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                            IconButton(onClick = { /* Handle editing phone number */ }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit phone number"
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            // Log out button
            Button(
                onClick = {
                    userPreferences.setLoggedIn(false)
                    onLogoutClicked()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log out")
            }
        }
    }
}
