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
        var createdAt: Long? = 0, var updatedAt: Long? = 0, var status: Int? = 0) : RealmObject()

