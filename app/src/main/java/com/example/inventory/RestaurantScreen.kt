package com.example.inventory

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.inventory.data.Menu
import com.example.inventory.data.Notification
import com.example.inventory.uiScreen.CustomerScreen.CustomerViewModel
import com.example.restaurantapp.uiScreen.CustomerScreen.CustomerMenuList
import com.example.restaurantapp.uiScreen.CustomerScreen.CustomerPayment
import com.example.restaurantapp.uiScreen.CustomerScreen.CustomerViewOrder
import com.example.restaurantapp.uiScreen.CustomerScreen.CustomerWelcome
import com.example.restaurantapp.uiScreen.CustomerScreen.StartCustomerScreen
import com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen.OwnerIncomingScreen
import com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen.OwnerMenuAddScreen
import com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen.OwnerMenuListScreen
import com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen.OwnerMenuUpdateScreen
import com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen.OwnerMenuViewModel
import com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen.OwnerSettingScreen
import com.example.restaurantapp.uiScreen.logInOwnerScreen.LoginScreen
import com.example.restaurantapp.uiScreen.logInOwnerScreen.OwnerPasswordScreen
import com.example.restaurantapp.uiScreen.logInOwnerScreen.SetRestaurantNameScreen
import com.example.restaurantapp.uiScreen.logInOwnerScreen.TransitionScreen
import com.example.restaurantapp.uiScreen.logInOwnerScreen.UserPreferences

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
    Owner_Settings(R.string.app_name),
    Owner_MenuList(R.string.app_name),
    Owner_Incoming(R.string.app_name)
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestaurantApp(
    navController: NavHostController = rememberNavController(),
    customerViewModel: CustomerViewModel = viewModel(),
    databaseViewModel: DatabaseViewModel = viewModel()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = RestaurantScreen.valueOf(
        backStackEntry?.destination?.route ?: RestaurantScreen.Start.name
    )
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val startDestination = RestaurantScreen.Start.name

    val customerUiState by customerViewModel.uiState.collectAsState()
    val currentPassword = customerUiState.currentPassword
    // Shared OwnerMenuViewModel to hold the selected menu for editing.
    val ownerMenuViewModel: OwnerMenuViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Login / Setup Flow
        composable(route = RestaurantScreen.Start.name) {
            LoginScreen(
                context = context,
                navController = navController,
                loginSuccess = {
                    if (userPreferences.isNewUser())
                        navController.navigate(RestaurantScreen.SetRestaurantName.name)
                    else
                        navController.navigate(RestaurantScreen.Owner_Transition.name)
                },
                databaseViewModel = databaseViewModel,
                customerViewModel = customerViewModel
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
                },
                onBackClicked = {
                    userPreferences.setLoggedIn(false)
                    navController.navigate(RestaurantScreen.Start.name) {
                        popUpTo(RestaurantScreen.Start.name) { inclusive = true }
                    }
                }
            )
        }
        composable(route = RestaurantScreen.Owner_Password.name) {
            var errorMessage by remember { mutableStateOf("") }
            OwnerPasswordScreen(
                navController = navController,
                enterPassword = { password ->
                    if (password == currentPassword) {
                        navController.navigate(RestaurantScreen.Owner_MenuList.name) {
                            popUpTo(RestaurantScreen.Owner_Password.name) { inclusive = true }
                        }
                    } else {
                        errorMessage = "Incorrect password. Try again."
                    }
                },
                errorMessage = errorMessage,
                onBackClicked = { navController.navigate(RestaurantScreen.Owner_Transition.name) }
            )
        }
        // Customer Flow
        composable(route = RestaurantScreen.Customer_SetTable.name) {
            StartCustomerScreen(
                modifier = Modifier.fillMaxSize(),
                customerViewModel = customerViewModel,
                databaseViewModel = databaseViewModel,
                onSetClicked = { navController.navigate(RestaurantScreen.Customer_Welcome.name) },
                onBackClicked = { navController.navigate(RestaurantScreen.Owner_Transition.name) }
            )
        }
        composable(route = RestaurantScreen.Customer_Welcome.name) {
            CustomerWelcome(
                modifier = Modifier.fillMaxSize(),
                customerViewModel = customerViewModel,
                databaseViewModel = databaseViewModel,
                OnStartOrderClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) }
            )
        }
        composable(route = RestaurantScreen.Customer_MenuList.name) {
            CustomerMenuList(
                modifier = Modifier.fillMaxSize(),
                customerViewModel = customerViewModel,
                databaseViewModel = databaseViewModel,
                onBillClicked = { navController.navigate(RestaurantScreen.Customer_Payment.name) },
                onViewOrderClicked = { navController.navigate(RestaurantScreen.Customer_ViewOrder.name) },
                onCallwaiterClicked = {
                    databaseViewModel.insertNotification(
                        Notification(
                            customerId = customerUiState.currentCustomerId,
                            title = "Call Waiter",
                            status = "Waiting"
                        )
                    )
                },
                onMenuClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) }
            )
        }
        composable(route = RestaurantScreen.Customer_ViewOrder.name) {
            CustomerViewOrder(
                modifier = Modifier.fillMaxSize(),
                customerViewModel = customerViewModel,
                databaseViewModel = databaseViewModel,
                onBillClicked = { navController.navigate(RestaurantScreen.Customer_Payment.name) },
                onViewOrderClicked = { navController.navigate(RestaurantScreen.Customer_ViewOrder.name) },
                onCallwaiterClicked = {
                    databaseViewModel.insertNotification(
                        Notification(
                            customerId = customerUiState.currentCustomerId,
                            title = "Call Waiter",
                            status = "Waiting"
                        )
                    )
                },
                onMenuClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) },
                onPlaceorderClicked = { }
            )
        }
        composable(route = RestaurantScreen.Customer_Payment.name) {
            CustomerPayment(
                modifier = Modifier.fillMaxSize(),
                customerViewModel = customerViewModel,
                databaseViewModel = databaseViewModel,
                onBillClicked = { navController.navigate(RestaurantScreen.Customer_Payment.name) },
                onViewOrderClicked = { navController.navigate(RestaurantScreen.Customer_ViewOrder.name) },
                onCallwaiterClicked = {
                    databaseViewModel.insertNotification(
                        Notification(
                            customerId = customerUiState.currentCustomerId,
                            title = "Call Waiter",
                            status = "Waiting"
                        )
                    )
                },
                onMenuClicked = { navController.navigate(RestaurantScreen.Customer_MenuList.name) },
                onPayClicked = {
                    navController.navigate(RestaurantScreen.Customer_SetTable.name)
                    databaseViewModel.insertNotification(
                        Notification(
                            customerId = customerUiState.currentCustomerId,
                            title = "Payment",
                            status = "Waiting"
                        )
                    )
                    customerViewModel.resetTable()
                }
            )
        }
        // Owner Flow
        // Add Menu Screen
        composable(route = RestaurantScreen.Owner_MenuAdd.name) {
            OwnerMenuAddScreen(
                restaurantName = "My Restaurant",
                ownerId = customerUiState.currentOwnerId,
                onMenuAdded = { navController.navigate(RestaurantScreen.Owner_MenuList.name) },
                onNavigateBack = { navController.navigate(RestaurantScreen.Owner_MenuList.name) },
                onIncomingClicked = { navController.navigate(RestaurantScreen.Owner_Incoming.name) },
                onManageClicked = { navController.navigate(RestaurantScreen.Owner_MenuList.name) },
                onHistoryClicked = { /* optional */ },
                onSettingClicked = { navController.navigate(RestaurantScreen.Owner_Settings.name) },
                databaseViewModel = databaseViewModel
            )
        }
        // Update/Delete Menu Screen
        composable(route = RestaurantScreen.Owner_MenuUpdate.name) {
            val selectedMenu = ownerMenuViewModel.selectedMenu.value
            if (selectedMenu != null) {
                OwnerMenuUpdateScreen(
                    restaurantName = "My Restaurant",
                    selectedMenu = selectedMenu,
                    databaseViewModel = databaseViewModel,
                    onDeleteSuccess = { navController.popBackStack() },
                    onUpdateSuccess = { navController.popBackStack() },
                    onNavigateBack = { navController.popBackStack() },
                    onIncomingClicked = { navController.navigate(RestaurantScreen.Owner_Incoming.name) },
                    onManageClicked = { navController.navigate(RestaurantScreen.Owner_MenuAdd.name) },
                    onHistoryClicked = { /* optional */ },
                    onSettingClicked = { navController.navigate(RestaurantScreen.Owner_Settings.name) }
                )
            }
        }
        // Settings Screen
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
                onIncomingClicked = { navController.navigate(RestaurantScreen.Owner_Incoming.name) },
                onManageClicked = { navController.navigate(RestaurantScreen.Owner_MenuAdd.name) },
                onHistoryClicked = { /* optional */ },
                onSettingClicked = { /* already here */ }
            )
        }
        // Menu List Screen with Edit Icon functionality
        composable(route = RestaurantScreen.Owner_MenuList.name) {
            OwnerMenuListScreen(
                ownerId = customerUiState.currentOwnerId,
                databaseViewModel = databaseViewModel,
                onMenuAdded = { navController.navigate(RestaurantScreen.Owner_MenuAdd.name) },
                onEditMenu = { menu ->
                    // Store the selected menu for editing and navigate to update screen.
                    ownerMenuViewModel.selectedMenu.value = menu
                    navController.navigate(RestaurantScreen.Owner_MenuUpdate.name)
                },
                onIncomingClicked = { navController.navigate(RestaurantScreen.Owner_Incoming.name) },
                onManageClicked = { /* optional */ },
                onHistoryClicked = { /* optional */ },
                onSettingClicked = { navController.navigate(RestaurantScreen.Owner_Settings.name) }
            )
        }
        composable(route = RestaurantScreen.Owner_Incoming.name) {
            OwnerIncomingScreen(
                onMenuAdded = { navController.navigate(RestaurantScreen.Owner_MenuAdd.name) },
                onIncomingClicked = { /* optional */ },
                onManageClicked = { navController.navigate(RestaurantScreen.Owner_MenuList.name) },
                onHistoryClicked = { /* optional */ },
                onSettingClicked = { navController.navigate(RestaurantScreen.Owner_Settings.name) },
                databaseViewModel = databaseViewModel
            )
        }
    }
}
