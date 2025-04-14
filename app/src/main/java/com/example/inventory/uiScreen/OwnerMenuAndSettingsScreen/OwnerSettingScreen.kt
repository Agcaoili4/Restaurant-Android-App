package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.logInOwnerScreen.UserPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerSettingScreen(
    modifier: Modifier = Modifier,
    userPreferences: UserPreferences,
    onLogoutClicked: () -> Unit,
    onNavigateBack: () -> Unit = {},
    onIncomingClicked: () -> Unit = {},
    onManageClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {},
    onSettingClicked: () -> Unit = {}
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
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            OwnerBottomAppBar(
                selectedIndex = 3,
                onIncomingClicked = onIncomingClicked,
                onManageClicked = onManageClicked,
                onHistoryClicked = onHistoryClicked,
                onSettingClicked = onSettingClicked
            )
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
            Text("Account", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text("Email: $email")
            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Text("Setting", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                            IconButton(onClick = { }) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit restaurant name")
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
                            IconButton(onClick = { }) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit phone number")
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
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
