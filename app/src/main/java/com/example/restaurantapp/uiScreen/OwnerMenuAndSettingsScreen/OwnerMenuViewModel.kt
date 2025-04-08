package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val price: String,
    val imageUrl: String
)

class OwnerMenuViewModel : ViewModel() {

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems.asStateFlow()

    private var currentId = 0

    fun addMenuItem(name: String, description: String, category: String, price: String, imageUrl: String) {
        val newItem = MenuItem(
            id = currentId++,
            name = name,
            description = description,
            category = category,
            price = price,
            imageUrl = imageUrl
        )
        _menuItems.update { oldList -> oldList + newItem }
    }

    // (Optional) Function to update a menu item if needed later
    fun updateMenuItem(
        itemId: Int,
        name: String,
        description: String,
        category: String,
        price: String,
        imageUrl: String
    ) {
        _menuItems.update { oldList ->
            oldList.map { item ->
                if (item.id == itemId) {
                    item.copy(
                        name = name,
                        description = description,
                        category = category,
                        price = price,
                        imageUrl = imageUrl
                    )
                } else item
            }
        }
    }

    fun getMenuItemById(itemId: Int): MenuItem? {
        return _menuItems.value.find { it.id == itemId }
    }
}
