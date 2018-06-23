package com.sobkisu.store.model

class Utils {
}


const val active = 1
const val deleted = 0
const val cash = 0
const val deposit = 1
const val synced = 1
const val notSynced = 0
const val status = "status"



enum class Activity {
    ManageProduct, ProductInfo, CashDetails, Deposit, CashOut
}

enum class Transaction {
    Buy, Sell, MissingType
}

open class BaseClass(var createdAt: Long = 0, var updatedAt: Long = 0, var status: Int = 0)