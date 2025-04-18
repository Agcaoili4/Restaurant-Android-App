package com.example.restaurantapp.uiScreen.CustomerScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inventory.DatabaseViewModel
import com.example.inventory.R
import com.example.inventory.theme.AppButton
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel

@Composable
fun CustomerWelcome(
    modifier: Modifier = Modifier,
    OnStartOrderClicked: () -> Unit,
    customerViewModel: CustomerViewModel,
    databaseViewModel: DatabaseViewModel,
) {
    val uiState by customerViewModel.uiState.collectAsState()
    val tableNumber = uiState.currentTableNumber

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.sushi),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome to Sushi Go 🍣",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "You're seated at Table $tableNumber",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            AppButton(
                onClick = OnStartOrderClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Your Order", fontSize = 16.sp, color = Color.White)
            }
        }
    }
}
