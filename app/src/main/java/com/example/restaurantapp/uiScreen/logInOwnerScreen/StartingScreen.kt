package com.example.restaurantapp.uiScreen.logInOwnerScreen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.RestaurantScreen

@Composable
fun LoginScreen(context: Context, navController: NavHostController, loginSuccess: () -> Unit) {
    val preference = remember { UserPreferences(context) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Can be removed once database is inserted, it's a hard coded credentials
    LaunchedEffect(Unit) {
        preference.saveCredentials("admin", "password123")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Restaurant App",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )

        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        Row(modifier = Modifier.fillMaxWidth()
            .padding(30.dp), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                if (preference.validateCredentials(username, password)) {
                    // Store login state
                    preference.setLoggedIn(true)
                    navController.navigate(RestaurantScreen.Owner_Transition.name)
                } else {
                    errorMessage = "Invalid username or password"
                }
            }) {
                Text("Login")
            }

            Button(onClick = {
                // New user then go to the set restaurant name
                preference.setNewUser(true)
                loginSuccess()
            }) {
                Text("Sign Up")
            }
        }
    }
}


@Preview
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current

    LoginScreen(
        context = context,
        navController = navController,
        loginSuccess = {}
    )
}
