package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.mancj.materialsearchbar.MaterialSearchBar
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
        var adapter = BuyCellAdapter(this, data, Transaction.Sell)
        recyclerViewBuyNowActivity.adapter = adapter

        var searchBar = findViewById<MaterialSearchBar>(R.id.searchBarSell)
        searchBar.setHint("Search..")
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                adapter.filter(data.filter {
                    it.productName?.contains(charSequence, true)!!
                } as ArrayList<Product>)
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })


    }

}
