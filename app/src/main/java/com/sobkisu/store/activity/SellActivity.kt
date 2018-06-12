package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sobkisu.store.R
import com.sobkisu.store.adapter.BuyCellAdapter
import com.sobkisu.store.db.ProductRepository
import com.sobkisu.store.model.Product
import com.sobkisu.store.model.Transaction
import kotlinx.android.synthetic.main.activity_buy.*

class SellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)
        recyclerViewBuyNowActivity.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<Product>()
        ProductRepository().getAllProduct().forEach {
            data.add(it!!)
        }
        recyclerViewBuyNowActivity.adapter = BuyCellAdapter(this, data, Transaction.Sell)


    }
}
