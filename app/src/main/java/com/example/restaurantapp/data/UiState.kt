package com.example.restaurantapp.data

data class UiState(
    val tableNumber: Int=0,
    val customerId: Int=0,
    val orderId: Int=0,
    val orderList: List<Order> = listOf(),

    val bottomBar:Int=1
)

data class Order(
    val menuId: Int,
    val quantity: Int
)

