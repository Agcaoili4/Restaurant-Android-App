package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Owner::class,Customer::class, Menu::class, Order::class, OrderDetail::class, Notification::class, Favorite::class, OrderStatusQueue::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ownerDao(): OwnerDao
    abstract fun customerDao(): CustomerDao
    abstract fun menuDao(): MenuDao
    abstract fun orderDao(): OrderDao
    abstract fun orderdetailDao(): OrderDetailDao
    abstract fun notificationDao(): NotificationDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun orderStatusQueueDao(): OrderStatusQueueDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "restaurant_database"
                ).fallbackToDestructiveMigration()
                    .build()

//                    .build().also {
//                        INSTANCE = it
//                    }
            }
        }
    }
}