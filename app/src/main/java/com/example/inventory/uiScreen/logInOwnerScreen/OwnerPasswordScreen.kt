package com.example.restaurantapp.uiScreen.logInOwnerScreen

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.inventory.R
import com.example.inventory.theme.AppButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerPasswordScreen(
    navController: NavHostController,
    enterPassword: (String) -> Unit,
    errorMessage: String,
    onBackClicked: () -> Unit,
) {
    var password by remember { mutableStateOf("") }

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
                        text = "Re-enter Password",
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
        Box(modifier = Modifier.fillMaxSize()) {
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

            // Foreground UI with innerPadding
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enter Owner Password",
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password", color = Color.White) },
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = LocalTextStyle.current.copy(color = Color.White),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFC8EAC),
                        unfocusedBorderColor = Color.White,
                        focusedLabelColor = Color(0xFFFC8EAC),
                        unfocusedLabelColor = Color.White,
                        cursorColor = Color(0xFFFC8EAC)
                    )
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color(0xFFFC8EAC),
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

                AppButton(
                    onClick = { enterPassword(password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Text("Submit", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}
