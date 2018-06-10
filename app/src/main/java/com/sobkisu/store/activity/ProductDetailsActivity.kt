package com.sobkisu.store.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sobkisu.store.R
import com.sobkisu.store.db.ProductRepository
import com.sobkisu.store.utils.log
import com.sobkisu.store.utils.toast

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        getProductDetails()

    }

    private fun getProductDetails() {
        val productId = intent.getLongExtra("productId", (-1).toLong())
        if (productId != (-1).toLong()) {
            val product = ProductRepository().getProductById(productId)
            log(product.productName!!)
            toast(product.productName!!)
        }
    }
}
