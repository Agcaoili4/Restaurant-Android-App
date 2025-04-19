package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OwnerDao {
    @Insert
    suspend fun insertOwner(owner: Owner)

    @Query("SELECT * FROM Owner")
    fun getAllOwners(): Flow<List<Owner>>

    @Query("SELECT * FROM Owner WHERE email = :email ORDER BY ownerId DESC")
    fun getOwnerByEmail(email: String): Flow<Owner?>
}

@Dao
interface CustomerDao {
    @Insert
    suspend fun insertCustomer(customer: Customer)

    @Query("SELECT * FROM Customer")
    fun getAllCustomers(): Flow<List<Customer>>

    @Query("SELECT * FROM Customer WHERE customerId = :customerId")
    fun getCustomerById(customerId: Int): Flow<Customer>
}

@Dao
interface MenuDao {
    @Insert
    suspend fun insertMenu(menu: Menu)

    @Update
    suspend fun updateMenu(menu: Menu)

    @Delete
    suspend fun deleteMenu(menu: Menu)

    @Query("SELECT * FROM Menu WHERE menuId = :menuId")
    fun getMenuById(menuId: Int): Flow<Menu?>

    @Query("SELECT * FROM Menu WHERE ownerId = :ownerId AND category = :category")
    fun getMenuByCategory(ownerId: Int, category: String): Flow<List<Menu>>

    @Query("SELECT * FROM Menu WHERE ownerId = :ownerId")
    fun getAllMenus(ownerId: Int): Flow<List<Menu>>
}

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM `Order`")
    fun getAllOrders(): Flow<List<Order>>
}

@Dao
interface OrderDetailDao {
    @Insert
    suspend fun insertOrderDetail(orderDetail: OrderDetail)

    @Query("SELECT * FROM OrderDetail WHERE orderId = :orderId")
    fun getAllOrderDetails(orderId: Int): Flow<List<OrderDetail>>
}

@Dao
interface NotificationDao {
    @Insert
    suspend fun insertNotification(notification: Notification)

    @Query("SELECT * FROM Notification")
    fun getAllNotifications(): Flow<List<Notification>>
}

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Delete
    suspend fun removeFavorite(favorite: Favorite)

    @Query("SELECT * FROM Favorite")
    fun getAllFavorites(): Flow<List<Favorite>>
}
