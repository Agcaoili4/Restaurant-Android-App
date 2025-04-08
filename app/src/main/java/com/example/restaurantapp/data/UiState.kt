package com.example.restaurantapp.data

data class UiState(
    val tableNumber: Int=15,
    val customerId: Int=0,
    val orderId: Int=0,
    val orderList: List<Order> = listOf()
)

data class Order(
    val menuId: Int,
    val quantity: Int
)