/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.inventory

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.inventory.data.AppDatabase
import com.example.inventory.data.AppRepository
import com.example.inventory.ui.theme.RestaurantAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: DatabaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // delete database and data from previous run
        applicationContext.deleteDatabase("restaurant_database")

        // create database
        val db = AppDatabase.getDatabase(this)
        val repository = AppRepository(
            db.ownerDao(),
            db.customerDao(),
            db.menuDao(),
            db.orderDao(),
            db.orderdetailDao(),
            db.notificationDao()
        )
        viewModel =
            ViewModelProvider(this, DatabaseViewModelFactory(repository))[DatabaseViewModel::class.java]

        // insert mockup data from start
        viewModel.insertStarterData()


        setContent {
            RestaurantAppTheme{
                RestaurantApp(databaseViewModel =viewModel)
            }
        }


    }
}
