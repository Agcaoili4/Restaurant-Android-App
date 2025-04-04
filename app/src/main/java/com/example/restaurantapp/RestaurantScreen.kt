package com.example.restaurantapp

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.uiScreen.CustomerMenuList
import com.example.restaurantapp.uiScreen.CustomerPayment
import com.example.restaurantapp.uiScreen.CustomerViewOrder
import com.example.restaurantapp.uiScreen.CustomerWelcome
import com.example.restaurantapp.uiScreen.StartAppScreen
import com.example.restaurantapp.uiScreen.components.RestaurantAppBar
import com.example.restaurantapp.uiScreen.logInOwnerScreen.LoginScreen
import com.example.restaurantapp.uiScreen.logInOwnerScreen.OwnerPasswordScreen
import com.example.restaurantapp.uiScreen.logInOwnerScreen.SetRestaurantNameScreen
import com.example.restaurantapp.uiScreen.logInOwnerScreen.TransitionScreen
import com.example.restaurantapp.uiScreen.logInOwnerScreen.UserPreferences


enum class RestaurantScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    SetRestaurantName(title = R.string.app_name),
    Owner_Transition(title = R.string.app_name),
    Owner_Password(title = R.string.app_name),
    Customer_Welcome(title = R.string.app_name),
    Customer_MenuList(title = R.string.app_name),
    Customer_ViewOrder(title = R.string.app_name),
    Customer_Payment(title = R.string.app_name),
    StartApp(title = R.string.app_name)
}

@OptIn(ExperimentalMaterial3Api::class)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestaurantApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RestaurantScreen.valueOf(
        backStackEntry?.destination?.route ?: RestaurantScreen.Start.name
    )
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    // Ensure the app always starts from the login screen
    val startDestination = RestaurantScreen.Start.name

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        // Login screen
        composable(route = RestaurantScreen.Start.name) {
            LoginScreen(
                context = context,
                navController = navController,
                loginSuccess = {
                    if (userPreferences.isNewUser()) {
                        navController.navigate(RestaurantScreen.SetRestaurantName.name)
                    } else {
                        navController.navigate(RestaurantScreen.Owner_Transition.name)
                    }
                }
            )
        }

        // Set Restaurant Name for new user
        composable(route = RestaurantScreen.SetRestaurantName.name) {
            SetRestaurantNameScreen(
                onNameSet = {
                    userPreferences.setNewUser(false)
                    navController.navigate(RestaurantScreen.Owner_Transition.name)
                }
            )
        }

        // Owner Transition Screen
        composable(route = RestaurantScreen.Owner_Transition.name) {
            TransitionScreen(
                select = { selection ->
                    if (selection == "Table Number") {
                        navController.navigate(RestaurantScreen.StartApp.name)
                    } else {
                        navController.navigate(RestaurantScreen.Owner_Password.name)
                    }
                }
            )
        }

        // Owner Password Screen
        composable(route = RestaurantScreen.Owner_Password.name) {
            var errorMessage by remember { mutableStateOf("") }

            OwnerPasswordScreen(
                navController = navController,
                enterPassword = { password ->
                    if (password == "password123") {
                        navController.navigate(RestaurantScreen.Owner_Transition.name)
                    } else {
                        errorMessage = "Error, please try again"
                    }
                },
                errorMessage = errorMessage
            )
        }

        // Start App screen
        composable(route = RestaurantScreen.StartApp.name) {
            StartAppScreen(
                modifier = Modifier.fillMaxSize(),
                onTempYositaButton = { navController.navigate(RestaurantScreen.Customer_Welcome.name) }
            )
        }

        // Customer Welcome Screen
        composable(route = RestaurantScreen.Customer_Welcome.name) {
            CustomerWelcome(
                modifier = Modifier.fillMaxSize(),
                OnStartOrderClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) }
            )
        }

        // Customer Menu Screen
        composable(route = RestaurantScreen.Customer_MenuList.name) {
            CustomerMenuList(
                modifier = Modifier.fillMaxSize(),
                onBillClicked = { navController.navigate(RestaurantScreen.Customer_Payment.name) },
                onViewOrderClicked = { navController.navigate(RestaurantScreen.Customer_ViewOrder.name) }
            )
        }

        // Customer View Order Screen
        composable(route = RestaurantScreen.Customer_ViewOrder.name) {
            CustomerViewOrder(
                modifier = Modifier.fillMaxSize(),
                onBillClicked = { navController.navigate(RestaurantScreen.Customer_Payment.name) },
                onPlaceOrderClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) }
            )
        }

        // Customer Payment Screen
        composable(route = RestaurantScreen.Customer_Payment.name) {
            CustomerPayment(
                modifier = Modifier.fillMaxSize(),
                onPayClicked = { navController.navigate(RestaurantScreen.Customer_ViewOrder.name) }
            )
        }
    }
}

@Preview
@Composable
fun PreviewRestaurantApp(){
    RestaurantApp()
}
