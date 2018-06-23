package com.sobkisu.store.db

import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.CashDepositInfo
import com.sobkisu.store.model.cash
import com.sobkisu.store.model.deposit
import io.realm.Realm


class CashDepositRepository() {


    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfiguration())

    fun saveDepositOrCashOut(item: CashDepositInfo, type: Int): Boolean {
        var isSuccessful = false

        return try {
            realm.executeTransaction {
                val id = it.where(CashDepositInfo::class.java).findAll().size
                it.copyToRealm(CashDepositInfo(Id = id.toLong() + 1, cashDepositAmount = item.cashDepositAmount,
                        cashDepositNotes = item.cashDepositNotes, cashDepositCollectorName = item.cashDepositCollectorName, cashDepositType = type))
            }
            if (deposit == type) {
                isSuccessful = AccountBalanceRepository().updateDeposit(item.cashDepositAmount!!)
            } else if (cash == type) {
                isSuccessful = AccountBalanceRepository().updateCashOut(item.cashDepositAmount!!)
            }
            isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            isSuccessful
        }
    }


}
