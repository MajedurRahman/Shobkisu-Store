package com.sobkisu.store.db

import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.AccountBalance
import com.sobkisu.store.utils.log
import io.realm.Realm

class AccountBalanceRepository() {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfiguration())


    fun updateDeposit(item: Int) {

        realm.executeTransaction {
            val alreadyExists = it.where(AccountBalance::class.java).findFirst()
            if (alreadyExists == null) {
                it.copyToRealm(AccountBalance(Id = 0.toLong(), currentBalance = 0, totalCashOut = 0, totalDeposit = 0))
            }
            it.where(AccountBalance::class.java).findFirst().apply {
                this!!.totalDeposit = this.totalDeposit!!.plus(item)
                this.currentBalance = this.currentBalance!!.plus(item)
            }
            it.where(AccountBalance::class.java).findFirst()!!.also {
                log("Current Account Balanced : ${it.currentBalance}    Total Deposit : ${it.totalDeposit}   Total CashOut : ${it.totalCashOut}")
            }
        }
    }

    fun updateCashOut(item: Int) {

        val alreadyExists = realm.where(AccountBalance::class.java).findFirst()

        if (alreadyExists == null) {
            realm.copyToRealm(AccountBalance(Id = 0.toLong(), currentBalance = 0, totalCashOut = 0, totalDeposit = 0))
        }

        realm.executeTransaction {
            it.where(AccountBalance::class.java).findFirst().apply {
                this!!.totalCashOut = this.totalCashOut!!.plus(item)
                this.currentBalance = this.currentBalance!!.minus(item)
            }
            it.where(AccountBalance::class.java).findFirst()!!.also {
                log("Current Account Balanced : ${it.currentBalance}    Total Deposit : ${it.totalDeposit}   Total CashOut : ${it.totalCashOut}")
            }
        }


    }

}