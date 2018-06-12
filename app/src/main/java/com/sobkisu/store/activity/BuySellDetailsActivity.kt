package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sobkisu.store.R
import com.sobkisu.store.db.BuySellRepository
import com.sobkisu.store.utils.log
import java.text.SimpleDateFormat
import java.util.*

class BuySellDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_sell_details)



        initListView()
    }

    private fun initListView() {
        BuySellRepository().getAllBuySellDetails().forEach {

            val yourmilliseconds: Long = it.createdAt!!
            val sdf = SimpleDateFormat("MMM dd,yyyy HH:mm")
            var action = "";

            action = if (it.buySellType == 1) {
                "Buy"
            } else {
                "Sell"
            }
            val resultdate = Date(yourmilliseconds)
            log("Product ID : " + it.productId + " Product Price : " + it.productPrice +
                    " ActionType : " + action + " Event Time : " + sdf.format(resultdate).toString()
            )
        }
    }
}
