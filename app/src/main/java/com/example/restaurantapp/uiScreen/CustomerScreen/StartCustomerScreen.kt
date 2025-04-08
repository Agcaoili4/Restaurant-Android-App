package com.example.restaurantapp.uiScreen.CustomerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.uiScreen.ViewModel

@Composable
fun StartCustomerScreen(
    modifier: Modifier,
    viewModel: ViewModel,
    onTempYositaButton: () -> Unit,
    onSimranPartButton: () -> Unit
) {

    var inputTable by remember { mutableStateOf("") }

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
                viewModel.setTableNumber(inputTable.toInt())
                onTempYositaButton()
            }, modifier = Modifier.padding(8.dp)
        ) {
            Text("Set")
        }
    }

        Button(onClick = onSimranPartButton) {
            Text("Go to Simran part")
        }

}
