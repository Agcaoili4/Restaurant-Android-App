package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderStatusQueueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderStatusQueue(queue: OrderStatusQueue)

    @Query("SELECT * FROM OrderStatusQueue ORDER BY timestamp ASC")
    fun getAllStatuses(): Flow<List<OrderStatusQueue>>

    @Query("DELETE FROM OrderStatusQueue")
    suspend fun clearAll()
}