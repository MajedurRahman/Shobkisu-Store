package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.sobkisu.store.R
import com.sobkisu.store.adapter.ProductDetailsAdapter
import com.sobkisu.store.db.ProductRepository
import com.sobkisu.store.model.Product
import kotlinx.android.synthetic.main.activity_show_product.*

class ShowProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_product)
        initProductList()
    }

    private fun initProductList() {
        var data = ArrayList<Product>()
        ProductRepository().getAllProduct().forEach {
            data.add(it)
        }
        allProductRecyclerView.adapter = ProductDetailsAdapter(this, data!!)
        allProductRecyclerView.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?;
    }
}
