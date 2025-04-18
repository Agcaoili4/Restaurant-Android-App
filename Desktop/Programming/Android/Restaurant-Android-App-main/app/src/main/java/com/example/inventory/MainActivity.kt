package com.example.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.inventory.data.AppDatabase
import com.example.inventory.data.AppRepository
import androidx.lifecycle.ViewModelProvider
import com.example.inventory.ui.theme.RestaurantAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: DatabaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Delete previous database for testing (optional)
        applicationContext.deleteDatabase("restaurant_database")

        // Create database and repository
        val db = AppDatabase.getDatabase(this)
        val repository = AppRepository(
            db.ownerDao(),
            db.customerDao(),
            db.menuDao(),
            db.orderDao(),
            db.orderdetailDao(),
            db.notificationDao()
        )
        viewModel = ViewModelProvider(this, DatabaseViewModelFactory(repository))
            .get(DatabaseViewModel::class.java)

        // Insert starter data so sample menus appear.
        viewModel.insertStarterData()

        setContent {
            RestaurantAppTheme {
                RestaurantApp(databaseViewModel = viewModel)
            }
        }
    }
}
