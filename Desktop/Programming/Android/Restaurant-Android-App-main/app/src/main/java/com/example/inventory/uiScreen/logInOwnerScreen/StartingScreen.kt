package com.example.restaurantapp.uiScreen.logInOwnerScreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.inventory.DatabaseViewModel
import com.example.inventory.R
import com.example.inventory.RestaurantScreen
import com.example.inventory.theme.AppButton
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
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    //---database
    val owner by databaseViewModel.getOwnerByEmail(username).collectAsState(initial = null)
    val coroutineScope = rememberCoroutineScope()  // Remember coroutine scope for suspend function calls

    // Box for the background image and overlaying the UI elements
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background image with a soft blurred effect for aesthetic appeal
        Image(
            painter = painterResource(id = R.drawable.sushi),
            contentDescription = "Japanese Theme Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Semi-transparent overlay to make text readable and add a slight elegance
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
        )

        // Main UI content with custom styling
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sushi Go 🍣",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username", color = Color.White) },
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFC8EAC),
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color(0xFFFC8EAC),
                    unfocusedLabelColor = Color.White
                )
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                visualTransformation = PasswordVisualTransformation(),
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFC8EAC),
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color(0xFFFC8EAC),
                )
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color(0xFF93000A),
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Login Button with rounded corners and elegant color scheme
            Button(
                onClick = {
                    coroutineScope.launch {
                        // Collecting owner from the flow and checking credentials
                        if (owner != null && owner!!.password == password) {
                            // Valid credentials
                            customerViewModel.setPassword(password)
                            customerViewModel.setCurrentOwnerId(owner!!.ownerId)
                            navController.navigate(RestaurantScreen.Owner_Transition.name)
                        } else {
                            // Invalid credentials
                            errorMessage = "Invalid username or password"
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF000000),
                    contentColor = Color.White
                )

            ) {
                Text("Login", color = Color.White, fontSize = 16.sp)
            }

            // Commented-out sign-up button (if needed in future)
            // Button(onClick = {
            //     // New user then go to the set restaurant name
            //     preference.setNewUser(true)
            //     loginSuccess()
            // }, modifier = Modifier.padding(8.dp)) {
            //     Text("Sign Up")
            // }
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
