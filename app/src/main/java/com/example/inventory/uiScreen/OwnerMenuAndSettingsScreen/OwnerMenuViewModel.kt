package com.example.restaurantapp.uiScreen.OwnerMenuAndSettingsScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Menu
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OwnerMenuViewModel : ViewModel() {
    // (Optional) In‑memory list of menus if needed.
    private val _menuItems = MutableStateFlow<List<Menu>>(emptyList())
    val menuItems: StateFlow<List<Menu>> = _menuItems.asStateFlow()

    // Holds the currently selected menu item.
    val selectedMenu = mutableStateOf<Menu?>(null)

    fun addMenuItem(menu: Menu) {
        _menuItems.update { oldList -> oldList + menu }
    }

    fun updateMenuItem(updatedMenu: Menu) {
        _menuItems.update { oldList ->
            oldList.map { item ->
                if (item.menuId == updatedMenu.menuId) updatedMenu else item
            }
        }
    }

    fun getMenuItemById(itemId: Int): Menu? {
        return _menuItems.value.find { it.menuId == itemId }
    }
}
