package com.example.restaurantapp.uiScreen.CustomerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.inventory.DatabaseViewModel
import com.example.inventory.data.Customer
import com.example.inventory.data.Order
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel
import com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen.OwnerBottomAppBar

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
    var currentCustomerId = uiState.currentCustomerId


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { Text(text = "Set Table") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


//        LazyColumn {
//            items(menus) { menu ->
//                Text(text = "User: ${menu.name}")
//            }
//        }
//
//        Button(onClick = {
//            databaseViewModel.insertMenu(
//                com.example.inventory.data.Menu(
//                    ownerId = 3,
//                    name = "Sushi iiiii",
//                    category = "Sushi",
//                    description = "Assortment of fresh nigiri and rolls",
//                    price = 18.50
//                )
//            )
//        }) { Text("add menu") }

            Text(
                text = "Set Table Number", modifier = Modifier.padding(8.dp)
            )
            TextField(
                value = inputTable,
                label = { Text("Table") },
                onValueChange = {
                    inputTable = it

                }, modifier = Modifier.padding(8.dp)
            )


            Button(
                onClick = {
                    if (inputTable != "") {
                        customerViewModel.setTableNumber(inputTable.toInt())
                        databaseViewModel.insertCustomer(
                            Customer(
                                ownerId = 1,
                                tableNumber = inputTable
                            )
                        )
                        databaseViewModel.insertOrder(Order(customerId =currentCustomerId, status = "Waiting", datetime = ""))
                        onSetClicked()
                    }
                }, modifier = Modifier.padding(8.dp)
            ) {
                Text("Set")
            }
        }

    }


}
