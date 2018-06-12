package com.sobkisu.store.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Sell(@PrimaryKey var Id: Long? = null,
                var productId: Long? = null,
                var sellPrice: Int? = 0,
                var numberOfItem: Int? = 0,
                var status: Int? = 0,
                var createdAt: Long? = System.currentTimeMillis(),
                var updatedAt: Long? = System.currentTimeMillis()) : RealmObject()