package com.sobkisu.store.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Product(
        @PrimaryKey
        var Id: Long? = null,
        var productCategory: String? = "Unknown",
        var productName: String? = "Name Not Found",
        var productPrice: Int? = 0,
        var discountRate: Int? = -1,
        var createdAt: Long? = System.currentTimeMillis(), var updatedAt: Long? = System.currentTimeMillis(), var status: Int = 1) : RealmObject()


open class ProductImage(var Id: Long? = null, var productId: Long? = null, var imageName: String? = "No Name", var imageString: String? = "",
                        var createdAt: Long? = System.currentTimeMillis(), var updatedAt: Long? = System.currentTimeMillis(), var status: Int = active, var isSynced: Int? = notSynced) : RealmObject()
