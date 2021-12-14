package com.carolmusyoka.iprocureandroidtest.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carolmusyoka.iprocureandroidtest.data.repository.ProductsRepository

class ProductViewModelFactory(private val productsRepository: ProductsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(productsRepository) as T
    }
}