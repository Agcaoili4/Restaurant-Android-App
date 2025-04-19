package com.example.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Owner(
    @PrimaryKey(autoGenerate = true) val ownerId: Int = 0,
    val email: String,
    val password: String,
    val restaurantName: String
)

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true) val customerId: Int = 0,
    val ownerId: Int,
    val tableNumber: String
)

@Entity
data class Menu(
    @PrimaryKey(autoGenerate = true) val menuId: Int = 0,
    val ownerId: Int,
    val name: String,
    val category: String,
    val description: String,
    val price:Double,
    val image:String
)

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true) val orderId: Int = 0,
    val customerId: Int,
    val status: String,
    val datetime: String,
)

@Entity
data class OrderDetail(
    @PrimaryKey(autoGenerate = true) val orderDetailId: Int = 0,
    val orderId: Int,
    val menuId: Int,
    val quantity: Int,
)

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true) val notificationId: Int = 0,
    val customerId: Int,
    val title: String,
    val status: String,
)

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = true) val menuId: Int,
    val addedAt: Long
)

@Entity
data class OrderStatusQueue(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val orderId: Int,
    val status: String,
    val timestamp: Long
)

// Add order status enumeration
enum class OrderStatus {
    Accepted,
    Preparing,
    Ready
}

