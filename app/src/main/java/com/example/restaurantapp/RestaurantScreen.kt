package com.example.restaurantapp

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.R
import com.example.restaurantapp.uiScreen.CustomerScreen.*
import com.example.restaurantapp.uiScreen.logInOwnerScreen.*
import com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen.*
import com.example.restaurantapp.uiScreen.ViewModel

enum class RestaurantScreen(@StringRes val title: Int) {
    Start(R.string.app_name),
    SetRestaurantName(R.string.app_name),
    Owner_Transition(R.string.app_name),
    Owner_Password(R.string.app_name),
    Customer_Welcome(R.string.app_name),
    Customer_MenuList(R.string.app_name),
    Customer_ViewOrder(R.string.app_name),
    Customer_Payment(R.string.app_name),
    Customer_SetTable(R.string.app_name),
    Owner_MenuAdd(R.string.app_name),
    Owner_MenuUpdate(R.string.app_name),
    Owner_Settings(R.string.app_name)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestaurantApp(
    navController: NavHostController = rememberNavController(),
    viewModel: ViewModel = viewModel()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RestaurantScreen.valueOf(
        backStackEntry?.destination?.route ?: RestaurantScreen.Start.name
    )
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val startDestination = RestaurantScreen.Start.name

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Login / Setup
        composable(route = RestaurantScreen.Start.name) {
            LoginScreen(
                context = context,
                navController = navController,
                loginSuccess = {
                    if (userPreferences.isNewUser())
                        navController.navigate(RestaurantScreen.SetRestaurantName.name)
                    else
                        navController.navigate(RestaurantScreen.Owner_Transition.name)
                }
            )
        }

        composable(route = RestaurantScreen.SetRestaurantName.name) {
            SetRestaurantNameScreen(
                onNameSet = {
                    userPreferences.setNewUser(false)
                    navController.navigate(RestaurantScreen.Owner_Transition.name)
                }
            )
        }

        composable(route = RestaurantScreen.Owner_Transition.name) {
            TransitionScreen(
                select = { selection ->
                    if (selection == "Table Number") {
                        navController.navigate(RestaurantScreen.Customer_SetTable.name)
                    } else {
                        navController.navigate(RestaurantScreen.Owner_Password.name)
                    }
                }
            )
        }

        composable(route = RestaurantScreen.Owner_Password.name) {
            var errorMessage by remember { mutableStateOf("") }
            OwnerPasswordScreen(
                navController = navController,
                enterPassword = { password ->
                    if (password == "password123") {
                        navController.navigate(RestaurantScreen.Owner_MenuAdd.name) {
                            popUpTo(RestaurantScreen.Owner_Password.name) { inclusive = true }
                        }
                    } else {
                        errorMessage = "Incorrect password. Try again."
                    }
                },
                errorMessage = errorMessage
            )
        }

        // Customer Flow
        composable(route = RestaurantScreen.Customer_SetTable.name) {
            StartCustomerScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                onTempYositaButton = { navController.navigate(RestaurantScreen.Customer_Welcome.name) },
                onSimranPartButton = {}
            )
        }

        composable(route = RestaurantScreen.Customer_Welcome.name) {
            CustomerWelcome(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                OnStartOrderClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) }
            )
        }

        composable(route = RestaurantScreen.Customer_MenuList.name) {
            CustomerMenuList(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                onBillClicked = { navController.navigate(RestaurantScreen.Customer_Payment.name) },
                onViewOrderClicked = { navController.navigate(RestaurantScreen.Customer_ViewOrder.name) },
                onCallwaiterClicked = {},
                onMenuClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) }
            )
        }

        composable(route = RestaurantScreen.Customer_ViewOrder.name) {
            CustomerViewOrder(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                onBillClicked = { navController.navigate(RestaurantScreen.Customer_Payment.name) },
                onViewOrderClicked = { navController.navigate(RestaurantScreen.Customer_ViewOrder.name) },
                onCallwaiterClicked = {},
                onMenuClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) },
                onPlaceorderClicked = {}
            )
        }

        composable(route = RestaurantScreen.Customer_Payment.name) {
            CustomerPayment(
                modifier = Modifier.fillMaxSize(),
                viewModel = viewModel,
                onBillClicked = { navController.navigate(RestaurantScreen.Customer_Payment.name) },
                onViewOrderClicked = { navController.navigate(RestaurantScreen.Customer_ViewOrder.name) },
                onCallwaiterClicked = {},
                onMenuClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) },
                onPayClicked = {
                    navController.navigate(RestaurantScreen.Customer_SetTable.name)
                    viewModel.resetTable()
                }
            )
        }

        // Owner Flow
        composable(route = RestaurantScreen.Owner_MenuAdd.name) {
            OwnerMenuAddScreen(
                restaurantName = "My Restaurant",
                onMenuAdded = { navController.navigate(RestaurantScreen.Owner_MenuUpdate.name) },
                onNavigateBack = { navController.popBackStack() },
                onIncomingClicked = { /* Optional */ },
                onManageClicked = { navController.navigate(RestaurantScreen.Owner_MenuAdd.name) },
                onHistoryClicked = { /* Do nothing */ },
                onSettingClicked = { navController.navigate(RestaurantScreen.Owner_Settings.name) }
            )
        }

        composable(route = RestaurantScreen.Owner_MenuUpdate.name) {
            OwnerMenuUpdateScreen(
                restaurantName = "My Restaurant",
                onDeleteClicked = { navController.popBackStack() },
                onUpdateClicked = { navController.popBackStack() },
                onNavigateBack = { navController.popBackStack() },
                onIncomingClicked = {},
                onManageClicked = { navController.navigate(RestaurantScreen.Owner_MenuAdd.name) },
                onHistoryClicked = { /* Do nothing */ },
                onSettingClicked = { navController.navigate(RestaurantScreen.Owner_Settings.name) }
            )
        }

        composable(route = RestaurantScreen.Owner_Settings.name) {
            OwnerSettingScreen(
                userPreferences = userPreferences,
                onLogoutClicked = {
                    userPreferences.setLoggedIn(false)
                    navController.navigate(RestaurantScreen.Start.name) {
                        popUpTo(RestaurantScreen.Start.name) { inclusive = true }
                    }
                },
                onNavigateBack = { navController.popBackStack() },
                onIncomingClicked = { /* optional */ },
                onManageClicked = { navController.navigate(RestaurantScreen.Owner_MenuAdd.name) },
                onHistoryClicked = { /* optional */ },
                onSettingClicked = { /* already here */ }
            )
        }
    }
}

@Preview
@Composable
fun PreviewRestaurantApp() {
    RestaurantApp()
}
