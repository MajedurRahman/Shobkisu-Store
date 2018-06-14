package com.sobkisu.store.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class ProductTransaction(@PrimaryKey
                              var Id: Long? = null,
                              var productId: Long? = null,
                              var productAvailable: Int? = 0,
                              var productSold: Int? = 0,
                              var createdAt: Long? = System.currentTimeMillis(), var updatedAt: Long? = System.currentTimeMillis(), var status: Int = 1) : RealmObject()


open class BuySellInfo(@PrimaryKey
                       var Id: Long? = null,
                       var productId: Long? = null,
                       var productName: String? = null,
                       var productPrice: Int? = 0,
                       var productCount: Int? = 0,
                       var buySellType: Int? = -1,
                       var createdAt: Long? = System.currentTimeMillis(), var updatedAt: Long? = System.currentTimeMillis(), var status: Int = 1) : RealmObject()



