package com.carolmusyoka.iprocureandroidtest.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.carolmusyoka.iprocureandroidtest.data.model.Products

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Products)

    @Delete
    suspend fun delete(product: Products)

    //get all data from database
    @Query("SELECT * FROM product_items")
    fun getAllProductItems(): LiveData<List<Products>>


}