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
import com.example.inventory.DatabaseViewModel
import com.example.inventory.RestaurantScreen
import com.example.inventory.data.Menu
import com.example.inventory.data.Owner
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    context: Context,
    navController: NavHostController,
    loginSuccess: () -> Unit,
    databaseViewModel: DatabaseViewModel,
    customerViewModel: CustomerViewModel
) {
    val preference = remember { UserPreferences(context) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Can be removed once database is inserted, it's a hard coded credentials
    LaunchedEffect(Unit) {
        preference.saveCredentials("admin", "password123")
    }

    //---database
    val owner by databaseViewModel.getOwnerByEmail(username).collectAsState(initial = null)
    val coroutineScope =
        rememberCoroutineScope()  // Remember coroutine scope for suspend function calls


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
            ),
            modifier = Modifier.padding(20.dp)
        )

        TextField(value = username,
            onValueChange = { username = it },
            label = { Text("Username") })

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {

//                    -----------old code
//                if (preference.validateCredentials(username, password)) {
//                    // Store login state
//                    preference.setLoggedIn(true)
//                    navController.navigate(RestaurantScreen.Owner_Transition.name)
//                } else {
//                    errorMessage = "Invalid username or password"
//                }
//-------------end old code


                    // ---- check on database (just test! you can change)
                    coroutineScope.launch {
                        // Collecting owner from the flow and checking credentials
                        if (owner != null && owner!!.password == password) {
                            // Valid credentials
//                        preference.setLoggedIn(true)
                            customerViewModel.setPassword(password)
                            customerViewModel.setCurrentOwnerId(owner!!.ownerId)
                            navController.navigate(RestaurantScreen.Owner_Transition.name)
                        } else {
                            // Invalid credentials
                            errorMessage = "Invalid username or password"
                        }
                        // ------ end check on database
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Login")
            }

            Button(onClick = {
                // New user then go to the set restaurant name
                preference.setNewUser(true)
                loginSuccess()
            }, modifier = Modifier.padding(8.dp)) {
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

//    LoginScreen(
//        context = context,
//        navController = navController,
//        loginSuccess = {}
//    )
}
