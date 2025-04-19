package com.example.inventory.data

import kotlinx.coroutines.flow.Flow


class AppRepository(
    private val ownerDao: OwnerDao,
    private val customerDao: CustomerDao,
    private val menuDao: MenuDao,
    private val orderDao: OrderDao,
    private val orderDetailDao: OrderDetailDao,
    private val notificationDao: NotificationDao,
    private val favoriteDao: FavoriteDao,
    private val orderStatusQueueDao: OrderStatusQueueDao
) {

    // Owner operations
    suspend fun insertOwner(owner: Owner) = ownerDao.insertOwner(owner)
    fun getAllOwners(): Flow<List<Owner>> = ownerDao.getAllOwners()
    fun getOwnerByEmail(email:String):Flow<Owner?> = ownerDao.getOwnerByEmail(email)

    // Customer operations
    suspend fun insertCustomer(customer: Customer) = customerDao.insertCustomer(customer)
    fun getAllCustomers(): Flow<List<Customer>> = customerDao.getAllCustomers()
    fun getCustomerById(customerId:Int): Flow<Customer?> = customerDao.getCustomerById(customerId)

    // Menu operations
    suspend fun insertMenu(menu: Menu) = menuDao.insertMenu(menu)
    suspend fun updateMenu(menu: Menu) = menuDao.updateMenu(menu)
    suspend fun deleteMenu(menu: Menu) = menuDao.deleteMenu(menu)
    fun getAllMenus(ownerId:Int): Flow<List<Menu>> = menuDao.getAllMenus(ownerId)
    fun getMenuById(menuId:Int): Flow<Menu?> = menuDao.getMenuById(menuId)
    fun getMenuByCategory(ownerId: Int,category: String): Flow<List<Menu>> = menuDao.getMenuByCategory(ownerId,category)

    // Order operations
    suspend fun insertOrder(order: Order) = orderDao.insertOrder(order)
    fun getAllOrders(): Flow<List<Order>> = orderDao.getAllOrders()

    // OrderDetail operations
    suspend fun insertOrderDetail(orderDetail: OrderDetail) = orderDetailDao.insertOrderDetail(orderDetail)
    fun getAllOrderDetails(orderId:Int): Flow<List<OrderDetail>> = orderDetailDao.getAllOrderDetails(orderId)

    // Notification operations
    suspend fun insertNotification(notification: Notification) = notificationDao.insertNotification(notification)
    fun getAllNotification(): Flow<List<Notification>> = notificationDao.getAllNotifications()

    // Favorites operations
    suspend fun addFavorite(favorite: Favorite) = favoriteDao.addFavorite(favorite)
    suspend fun removeFavorite(favorite: Favorite) = favoriteDao.removeFavorite(favorite)
    fun getAllFavorites(): Flow<List<Favorite>> = favoriteDao.getAllFavorites()

    // Order status queue operations
    suspend fun enqueueOrderStatus(orderId: Int, status: String, timestamp: Long) =
        orderStatusQueueDao.insertOrderStatusQueue(
            OrderStatusQueue(orderId = orderId, status = status, timestamp = timestamp)
        )
    fun getQueuedStatuses(): Flow<List<OrderStatusQueue>> = orderStatusQueueDao.getAllStatuses()
    suspend fun clearQueuedStatuses() = orderStatusQueueDao.clearAll()

}