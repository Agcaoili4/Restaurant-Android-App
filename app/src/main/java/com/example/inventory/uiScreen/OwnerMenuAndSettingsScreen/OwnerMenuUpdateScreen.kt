package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventory.DatabaseViewModel
import com.example.inventory.data.Menu
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerMenuUpdateScreen(
    restaurantName: String = "My Restaurant",
    selectedMenu: Menu,
    databaseViewModel: DatabaseViewModel,
    onDeleteSuccess: () -> Unit,
    onUpdateSuccess: () -> Unit,
    onNavigateBack: () -> Unit,
    onIncomingClicked: () -> Unit,
    onManageClicked: () -> Unit,
    onHistoryClicked: () -> Unit,
    onSettingClicked: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var name = remember { mutableStateOf(selectedMenu.name) }
    var description = remember { mutableStateOf(selectedMenu.description) }
    var category = remember { mutableStateOf(selectedMenu.category) }
    var price = remember { mutableStateOf(selectedMenu.price.toString()) }
    var imageUrl = remember { mutableStateOf(selectedMenu.image) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = restaurantName) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFEEEEEE))
            )
        },
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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Edit Menu", fontSize = 20.sp)
            OwnerMenuUpdateTextField(
                value = name.value,
                label = "Menu Name",
                onValueChange = { name.value = it }
            )
            OwnerMenuUpdateTextField(
                value = description.value,
                label = "Description",
                onValueChange = { description.value = it }
            )
            OwnerMenuUpdateTextField(
                value = category.value,
                label = "Category",
                onValueChange = { category.value = it }
            )
            OwnerMenuUpdateTextField(
                value = price.value,
                label = "Price",
                onValueChange = { price.value = it }
            )
            OwnerMenuUpdateTextField(
                value = imageUrl.value,
                label = "Image URL",
                onValueChange = { imageUrl.value = it }
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            databaseViewModel.deleteMenu(selectedMenu)
                            onDeleteSuccess()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Delete", color = Color.White)
                }
                Button(
                    onClick = {
                        val updatedPrice = price.value.toDoubleOrNull() ?: selectedMenu.price
                        val updatedMenu = selectedMenu.copy(
                            name = name.value,
                            description = description.value,
                            category = category.value,
                            price = updatedPrice,
                            image = imageUrl.value
                        )
                        scope.launch {
                            databaseViewModel.updateMenu(updatedMenu)
                            onUpdateSuccess()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B3C92)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Update", color = Color.White)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerMenuUpdateTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 12.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}
