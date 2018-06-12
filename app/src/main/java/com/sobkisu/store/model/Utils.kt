package com.sobkisu.store.model

class Utils {
}


val active = 1
val deleted = 0;

enum class Activity {
    ManageProduct, ProductInfo
}

enum class Transaction {
    Buy, Sell, MissingType
}

open class BaseClass(var createdAt: Long = 0, var updatedAt: Long = 0, var status: Int = 0)