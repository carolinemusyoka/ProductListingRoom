package com.carolmusyoka.iprocureandroidtest.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.carolmusyoka.iprocureandroidtest.data.model.Products
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Products)

    @Delete
    suspend fun delete(product: Products)

    //get all data from database
    @Query("SELECT * FROM product_items")
    fun getAllProductItems(): LiveData<List<Products>>

    @Query("SELECT * FROM product_items WHERE productName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Products>>

}