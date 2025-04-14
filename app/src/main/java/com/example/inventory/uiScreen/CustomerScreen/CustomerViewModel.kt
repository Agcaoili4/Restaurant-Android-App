package com.example.inventory.uiScreen.CustomerScreen

import androidx.lifecycle.ViewModel
import com.example.inventory.data.Menu
import com.example.inventory.data.OrderDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CustomerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CustomerUiState())
    val uiState: StateFlow<CustomerUiState> = _uiState.asStateFlow()

    fun setBottomBar(bottomBar: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                bottomBar = bottomBar
            )

        }
    }

    fun setCurrentMenuList(menus: List<Menu>) {
        _uiState.update { currentState ->
            currentState.copy(
                currentMenuList = menus
            )
        }
    }

    fun setCurrentOwnerId(ownerId:Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentOwnerId = ownerId
            )
        }
    }

    fun setTableNumber(tableNumber: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentTableNumber = tableNumber
            )
        }
    }

    fun setCurrentOrderId(orderId:Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentOrderId = orderId
            )
        }
    }

    fun setTotalPrice(totalPrice: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                currentTotalPrice = totalPrice
            )
        }
    }

    fun setOrderList(menuId: Int, quantity: Int) {
        _uiState.update { currentState ->
            val updatedOrderList = currentState.currentOrderList.toMutableList()
            updatedOrderList.add(OrderDetail(orderId = currentState.currentOrderId, menuId = menuId, quantity = quantity))
            currentState.copy(
                currentOrderList = updatedOrderList
            )
        }
    }

    fun resetOrderList() {
        _uiState.update { currentState ->
            currentState.copy(
                currentOrderList = emptyList()
            )
        }


    }

    fun resetTable() {
        _uiState.update { currentState ->
            currentState.copy(
                currentOrderId = currentState.currentOrderId + 1,
                currentCustomerId = currentState.currentCustomerId + 1,
                currentTableNumber = 0,
                currentTotalPrice = 0.0,
                currentOrderList = emptyList(),
                bottomBar = 1,
                )
        }
    }


}