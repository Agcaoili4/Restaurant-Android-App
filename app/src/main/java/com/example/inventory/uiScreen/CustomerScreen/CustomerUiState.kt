package com.example.inventory.uiScreen.CustomerScreen

import com.example.inventory.data.Menu
import com.example.inventory.data.OrderDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class CustomerUiState(
    val currentTableNumber: Int=0,
    val currentCustomerId: Int=1,
    val currentOwnerId: Int=1,
    val currentOrderId: Int=1,
    val currentOrderList: List<OrderDetail> = listOf(),
    val currentTotalPrice: Double = 0.0,
    val currentMenuList: List<Menu> = listOf(),
    val bottomBar:Int=1
)

data class Order(
    val menuId: Int,
    val quantity: Int
)

