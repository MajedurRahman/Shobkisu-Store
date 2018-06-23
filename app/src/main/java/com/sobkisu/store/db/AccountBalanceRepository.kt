package com.sobkisu.store.db

import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.AccountBalance
import com.sobkisu.store.utils.log
import io.realm.Realm

class AccountBalanceRepository() {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfiguration())


    fun updateDeposit(item: Int): Boolean {
        var isSuccessful = false
        try {
            realm.executeTransaction {
                val alreadyExists = it.where(AccountBalance::class.java).findFirst()
                if (alreadyExists == null) {
                    it.copyToRealm(AccountBalance(Id = 0.toLong(), currentBalance = 0, totalCashOut = 0, totalDeposit = 0))
                }
                it.where(AccountBalance::class.java).findFirst().apply {
                    this!!.totalDeposit = this.totalDeposit!!.plus(item)
                    this.currentBalance = this.currentBalance!!.plus(item)
                    isSuccessful = true
                }
                it.where(AccountBalance::class.java).findFirst()!!.also {
                    log("Current Account Balanced : ${it.currentBalance}    Total Deposit : ${it.totalDeposit}   Total CashOut : ${it.totalCashOut}")
                }
            }
        } catch (e: Exception) {
            isSuccessful = false
        }

        return isSuccessful
    }

    fun updateCashOut(item: Int): Boolean {
        var isSuccessful = false
        val alreadyExists = realm.where(AccountBalance::class.java).findFirst()

        if (alreadyExists == null) {
            realm.copyToRealm(AccountBalance(Id = 0.toLong(), currentBalance = 0, totalCashOut = 0, totalDeposit = 0))
        }

        realm.executeTransaction {
            it.where(AccountBalance::class.java).findFirst().apply {
                isSuccessful = if (this!!.currentBalance!! >= item) {
                    this.totalCashOut = this.totalCashOut!!.plus(item)
                    this.currentBalance = this.currentBalance!!.minus(item)
                    true
                } else {
                    false
                }
            }
            it.where(AccountBalance::class.java).findFirst()!!.also {
                log("Current Account Balanced : ${it.currentBalance}    Total Deposit : ${it.totalDeposit}   Total CashOut : ${it.totalCashOut}")
            }
        }


        return isSuccessful
    }

}