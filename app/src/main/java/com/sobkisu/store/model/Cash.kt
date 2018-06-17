package com.sobkisu.store.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class CashDeposit(@PrimaryKey
                       var Id: Long? = null,
                       var cashDepositAmount: Int? = 0,
                       var cashDepositType: Int? = -1,
                       var cashDepositCollectorId: Int? = -1,
                       var cashDepositCollectorName: String? = "Unknown",
                       var cashDepositNotes: String? = "",
                       var createdAt: Long? = System.currentTimeMillis(),
                       var updatedAt: Long? = System.currentTimeMillis(),
                       var status: Int? = active,
                       var isSynced: Int? = 0) : RealmObject()

open class AccountBalance(@PrimaryKey
                          var Id: Long? = null,
                          var currentBalance: Int? = null,
                          var totalDeposit: Int? = null,
                          var totalCashOut: Int? = null,
                          var createdAt: Long? = System.currentTimeMillis(),
                          var updatedAt: Long? = System.currentTimeMillis(),
                          var status: Int? = active,
                          var isSynced: Int? = 0) : RealmObject()

