package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventory.DatabaseViewModel
import com.example.inventory.data.Menu
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerMenuAddScreen(
    restaurantName: String = "My Restaurant",
    ownerId: Int,
    databaseViewModel: DatabaseViewModel,
    onMenuAdded: () -> Unit,
    onNavigateBack: () -> Unit,
    onIncomingClicked: () -> Unit,
    onManageClicked: () -> Unit,
    onHistoryClicked: () -> Unit,
    onSettingClicked: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val menuName = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val imageUrl = remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text(restaurantName) }) },
        bottomBar = {
            OwnerBottomAppBar(
                selectedIndex = 1,
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
                .padding(20.dp)
        ) {
            Text("Add New Menu", fontSize = 20.sp)
            OutlinedTextField(
                value = menuName.value,
                onValueChange = { menuName.value = it },
                label = { Text("Menu Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = category.value,
                onValueChange = { category.value = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = price.value,
                onValueChange = { price.value = it },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = imageUrl.value,
                onValueChange = { imageUrl.value = it },
                label = { Text("Image URL") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    val priceVal = price.value.toDoubleOrNull() ?: 0.0
                    if (menuName.value.isNotBlank() && priceVal > 0.0) {
                        val newMenu = Menu(
                            ownerId = ownerId,
                            name = menuName.value,
                            category = category.value,
                            description = description.value,
                            price = priceVal,
                            image = imageUrl.value
                        )
                        scope.launch {
                            databaseViewModel.insertMenu(newMenu)
                            onMenuAdded() // Navigate back (e.g., to list)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B3C92))
            ) {
                Text("✓  Add Menu", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
