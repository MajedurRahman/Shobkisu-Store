package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sobkisu.store.R
import com.sobkisu.store.db.CashDepositRepository
import com.sobkisu.store.model.Activity
import com.sobkisu.store.model.CashDepositInfo
import com.sobkisu.store.model.cash
import com.sobkisu.store.model.deposit
import com.sobkisu.store.utils.negativeSnackBar
import com.sobkisu.store.utils.positiveSnackBar
import kotlinx.android.synthetic.main.activity_cash_in_out.*

class CashInOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_in_out)
        val fromActivity = intent.getSerializableExtra("from") as Activity

        if (fromActivity == Activity.Deposit) {
            saveCashOrDeposit(deposit)

            toolbarTextCashDeposit.text = "Deposit Information"
            shoppingCartImage.setBackgroundResource(R.drawable.ic_cash)

        } else if (fromActivity == Activity.CashOut) {
            saveCashOrDeposit(cash)
            toolbarTextCashDeposit.text = "Cash Out Information"
            shoppingCartImage.setBackgroundResource(R.drawable.ic_credit_card)
        }

    }

    private fun saveCashOrDeposit(type: Int) {
        depositOrCashOutSave.setOnClickListener {
            val cashierName = depositOrCashByName.text.toString()
            val cashOrDepositAmount = depositOrCashAmount.text.toString().toIntOrNull()
            val note = depositeOrCashNotes.text.toString()
            if (cashOrDepositAmount != null && cashierName.isNotEmpty()) {
                saveCashDeposit(cashierName, cashOrDepositAmount, note, type, it)
            } else {
                negativeSnackBar("One or More Information Is Missing ", it)
            }
        }


        depositOrCashOutCancel.setOnClickListener {
            finish()
        }
    }

    private fun saveCashDeposit(cashierName: String, cashOrDepositAmount: Int?, note: String, type: Int, it: View?) {
        if (CashDepositRepository().saveDepositOrCashOut(CashDepositInfo(cashDepositCollectorName = cashierName, cashDepositAmount = cashOrDepositAmount, cashDepositNotes = note), type)) {
            positiveSnackBar("Successfully Saved Information ", it)
        } else {
            negativeSnackBar("$cashOrDepositAmount BDT Is Not Available In Cash !!", it)
        }
    }
}
