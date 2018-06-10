package com.sobkisu.store.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category(var categoryName: String, var imageId: Int)
open class ProductCategory(@PrimaryKey var id: Long? = null, var pcName: String? = null, var createdAt: Long? = 0, var updatedAt: Long? = 0, var status: Int? = 0) : RealmObject()