package com.carolmusyoka.iprocureandroidtest.ui

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carolmusyoka.iprocureandroidtest.R
import com.carolmusyoka.iprocureandroidtest.data.model.Products
import com.carolmusyoka.iprocureandroidtest.databinding.ListItemsBinding

class AllProductsAdapter(var productItemList: List<Products>): RecyclerView.Adapter<AllProductsAdapter.AllProductsViewHolder>() {

    private lateinit var binding: ListItemsBinding
    private lateinit var context: Context
    private val _bitmapLivedata = MutableLiveData<Bitmap>()
    private val bitmapLiveData: LiveData<Bitmap> get() = _bitmapLivedata

    inner class AllProductsViewHolder(itemView: ListItemsBinding): RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductsViewHolder {
        context = parent.context
        binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AllProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllProductsViewHolder, position: Int) {
        val item = productItemList[position]

        holder.itemView.apply {
            binding.productName.text = item.productName
            binding.productCode.text = "#" + item.productCode.toString()
            binding.productCategory.text = item.category
            binding.productManufacturer.text = item.manufacturer + " Manufacturers"
            binding.unitCost.text = item.unitCost + "per unit"
            Glide.with(binding.imageView.context)
                .asBitmap()
                .placeholder(R.drawable.img)
                .load(item.imageAdded)
                .centerCrop()
                .into(binding.imageView)

        }
    }

    override fun getItemCount(): Int {
        return productItemList.size
    }
}