package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import com.mancj.materialsearchbar.MaterialSearchBar
import com.sobkisu.store.R
import com.sobkisu.store.adapter.BuySellDetailsAdapter
import com.sobkisu.store.db.BuySellRepository
import com.sobkisu.store.model.BuySellInfo
import kotlinx.android.synthetic.main.activity_buy_sell_details.*
import java.util.*

class BuySellDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_sell_details)
        initListView()
    }

    private fun initListView() {
        val data = BuySellRepository().getAllBuySellDetails()
        buySellRecyclerView.layoutManager = LinearLayoutManager(this)
        var adapter = BuySellDetailsAdapter(this, data)
        buySellRecyclerView.adapter = adapter


        var searchBar = findViewById<MaterialSearchBar>(R.id.searchBarBuySell)
        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //SEARCH FILTER

                adapter.filter(data.filter {
                    it.productName?.contains(charSequence, true)!!
                } as ArrayList<BuySellInfo>)
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

    }
}
