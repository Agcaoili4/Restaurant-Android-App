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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerMenuUpdateScreen(
    restaurantName: String = "restaurant name",
    modifier: Modifier = Modifier,
    onDeleteClicked: () -> Unit = {},
    onUpdateClicked: () -> Unit = {},
    onNavigateBack: () -> Unit = {},
    onIncomingClicked: () -> Unit = {},
    onManageClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {},
    onSettingClicked: () -> Unit = {}
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var category by remember { mutableStateOf(TextFieldValue("")) }
    var price by remember { mutableStateOf(TextFieldValue("")) }
    var imageUrl by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = restaurantName) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
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
                text = "Edit menu",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            OwnerMenuUpdateTextField(value = name, label = "Menu name") { name = it }
            OwnerMenuUpdateTextField(value = description, label = "Description") { description = it }
            OwnerMenuUpdateTextField(value = category, label = "Category") { category = it }
            OwnerMenuUpdateTextField(value = price, label = "Price") { price = it }
            OwnerMenuUpdateTextField(value = imageUrl, label = "Image upload") { imageUrl = it }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onDeleteClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Delete", color = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onUpdateClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B3C92)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Update", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun OwnerMenuUpdateTextField(value: TextFieldValue, label: String, onValueChange: (TextFieldValue) -> Unit) {
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