package com.carolmusyoka.iprocureandroidtest.data.repository

import com.carolmusyoka.iprocureandroidtest.data.db.ProductsDatabase
import com.carolmusyoka.iprocureandroidtest.data.model.Products

class ProductsRepository(private val productsDatabase: ProductsDatabase) {
    suspend fun insert(product: Products) = productsDatabase.getProductsDao().insert(product)
    suspend fun delete(product: Products) = productsDatabase.getProductsDao().delete(product)
    fun allProductItems() = productsDatabase.getProductsDao().getAllProductItems()
    fun searchDatabase(searchQuery: String) = productsDatabase.getProductsDao().searchDatabase(searchQuery)
}