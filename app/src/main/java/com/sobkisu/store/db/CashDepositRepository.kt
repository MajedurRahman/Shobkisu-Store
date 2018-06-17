package com.sobkisu.store.db

import com.sobkisu.store.SobKisuApplication
import com.sobkisu.store.model.CashDeposit
import com.sobkisu.store.model.cash
import com.sobkisu.store.model.deposit
import io.realm.Realm


class CashDepositRepository() {
    val realm: Realm = Realm.getInstance(SobKisuApplication().getTestConfiguration())

    fun saveDepositOrCashOut(item: CashDeposit, type: Int): Boolean {
        return try {
            realm.executeTransaction {
                val id = it.where(CashDeposit::class.java).findAll().size
                it.copyToRealm(CashDeposit(Id = id.toLong() + 1, cashDepositAmount = item.cashDepositAmount,
                        cashDepositNotes = item.cashDepositNotes, cashDepositCollectorName = item.cashDepositCollectorName, cashDepositType = type))

            }
            if (deposit == type) {
                AccountBalanceRepository().updateDeposit(item.cashDepositAmount!!)
            } else if (cash == type) {
                AccountBalanceRepository().updateCashOut(item.cashDepositAmount!!)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}
