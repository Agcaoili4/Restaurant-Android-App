package com.example.restaurantapp.uiScreen

import androidx.lifecycle.ViewModel
import com.example.restaurantapp.data.Order
import com.example.restaurantapp.data.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ViewModel :ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setTableNumber(tableNumber: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                tableNumber=tableNumber
            )
        }
    }

    fun setOrderList(menuId:Int,quantity:Int){
        _uiState.update { currentState ->
            val updatedOrderList = currentState.orderList.toMutableList()
            updatedOrderList.add(Order(menuId, quantity))
            currentState.copy(
                orderList = updatedOrderList
        ) }
    }

    fun resetOrderList(){
        _uiState.update { currentState ->
            currentState.copy(
                orderList = emptyList()
            )
        }
    }

    fun resetTable(){
        _uiState.value=UiState()
    }


}