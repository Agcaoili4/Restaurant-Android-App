package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventory.DatabaseViewModel
import com.example.inventory.data.Menu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerMenuAddScreen(
    restaurantName: String = "restaurant name",
    modifier: Modifier = Modifier,
    onMenuAdded: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    onIncomingClicked: () -> Unit = {},
    onManageClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {},
    onSettingClicked: () -> Unit = {},
    databaseViewModel: DatabaseViewModel,
) {
    var menuName by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var category by remember { mutableStateOf(TextFieldValue("")) }
    var price by remember { mutableStateOf(TextFieldValue("")) }
    var imageUrl by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { Text(text = restaurantName) }
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
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Add new menu",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            FloatingLabelInput(menuName, "Menu name") { menuName = it }
            FloatingLabelInput(description, "Description") { description = it }
            FloatingLabelInput(category, "Category") { category = it }
            FloatingLabelInput(price, "Price") { price = it }
            FloatingLabelInput(imageUrl, "Image upload") { imageUrl = it }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onMenuAdded,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B3C92)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("✓  Add menu", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun FloatingLabelInput(value: TextFieldValue, label: String, onValueChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 12.sp) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFAAAAAA),
            unfocusedBorderColor = Color(0xFFDDDDDD)
        )
    )
}
