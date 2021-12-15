package com.carolmusyoka.iprocureandroidtest.data.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_items")
data class Products(
    @ColumnInfo(name = "productCode")
    var productCode: Int ,

    @ColumnInfo(name = "productName")
    var productName: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "productType")
    var productType: String,

    @ColumnInfo(name = "manufacturer")
    var manufacturer: String,

    @ColumnInfo(name = "distributer")
    var distributer: String,

    @ColumnInfo(name = "vat")
    var vat: Boolean,

    @ColumnInfo(name = "unitCost")
    var unitCost: String,

    @ColumnInfo(name = "retailPrice")
    var retailPrice: String,

    @ColumnInfo(name = "agentPrice")
    var agentPrice: String,

    @ColumnInfo(name = "wholesalePrice")
    var wholesalePrice: String,

    @ColumnInfo(name = "imageAdded")
    var imageAdded: String,
){
    @PrimaryKey(autoGenerate = true)
    var  id: Int? = null
}