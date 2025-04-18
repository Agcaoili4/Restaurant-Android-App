package com.example.restaurantapp.uiScreen.CustomerScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventory.DatabaseViewModel
import com.example.inventory.R
import com.example.inventory.data.Customer
import com.example.inventory.data.Order
import com.example.inventory.theme.AppButton
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartCustomerScreen(
    modifier: Modifier,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
    onSetClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    var inputTable by remember { mutableStateOf("") }
    val uiState by customerViewModel.uiState.collectAsState()
    val currentCustomerId = uiState.currentCustomerId

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                title = {
                    Text(
                        text = "Set Table",
                        color = Color.White,
                        style = TextStyle(fontSize = 20.sp)
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF000000)
                )
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background image with overlay
            Image(
                painter = painterResource(id = R.drawable.sushi),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Semi-transparent overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
            )

            // Main content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Set Table Number",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color(0xFFFFFFFF)
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = inputTable,
                    onValueChange = { inputTable = it },
                    label = { Text("Table", color = Color.White) },
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(color = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFC8EAC),
                        unfocusedBorderColor = Color.White,
                        focusedLabelColor = Color(0xFFFC8EAC),
                        unfocusedLabelColor = Color.White
                    )
                )

                AppButton(
                    onClick = {
                        val tableNumber = inputTable.toIntOrNull()
                        if (tableNumber != null) {
                            customerViewModel.setTableNumber(tableNumber)
                            databaseViewModel.insertCustomer(
                                Customer(
                                    ownerId = 1,
                                    tableNumber = inputTable
                                )
                            )
                            databaseViewModel.insertOrder(
                                Order(
                                    customerId = currentCustomerId,
                                    status = "Waiting",
                                    datetime = ""
                                )
                            )
                            onSetClicked()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Set", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}
