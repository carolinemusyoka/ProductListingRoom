package com.carolmusyoka.iprocureandroidtest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carolmusyoka.iprocureandroidtest.data.model.Products


@Database(entities = [Products::class], version = 1, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun getProductsDao(): ProductsDao

    companion object{
        @Volatile
        private var instance: ProductsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
          context.applicationContext,
          ProductsDatabase::class.java,
         "products_db.db"
         ).fallbackToDestructiveMigration()
         .build()
    }
}