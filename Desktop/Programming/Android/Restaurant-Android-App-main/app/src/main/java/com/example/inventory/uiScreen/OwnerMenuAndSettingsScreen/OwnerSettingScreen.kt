package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
    userPreferences: UserPreferences,
    onLogoutClicked: () -> Unit,
    onNavigateBack: () -> Unit = {},
    onIncomingClicked: () -> Unit = {},
    onManageClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {},
    onSettingClicked: () -> Unit = {}
) {
    var email by remember { mutableStateOf("app@mybvc.ca") }
    var restaurantName by remember { mutableStateOf("My Restaurant") }
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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text("Account", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Email: $email")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Restaurant: $restaurantName")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Phone: $phoneNumber")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    userPreferences.setLoggedIn(false)
                    onLogoutClicked()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log out", color = Color.White)
            }
        }
    }
}
