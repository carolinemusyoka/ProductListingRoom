package com.carolmusyoka.iprocureandroidtest.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carolmusyoka.iprocureandroidtest.data.model.Products
import com.carolmusyoka.iprocureandroidtest.data.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel (private val productsRepository: ProductsRepository) : ViewModel() {

    val itemCount = MutableLiveData<Int>()

    fun setCount(count: Int){
        itemCount.value = count
    }



    fun insert(product: Products) = viewModelScope.launch {
        productsRepository.insert(product)
    }

    fun delete(product: Products) = viewModelScope.launch(Dispatchers.IO) {
        productsRepository.delete(product)
    }

    fun allProductItems() = productsRepository.allProductItems()



}